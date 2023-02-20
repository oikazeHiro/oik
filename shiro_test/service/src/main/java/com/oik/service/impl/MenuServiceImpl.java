package com.oik.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.oik.dao.entity.*;
import com.oik.dao.mapper.MenuMapper;
import com.oik.util.exception.Result;
import com.oik.util.exception.ResultUtil;
import com.oik.service.service.CacheService;
import com.oik.service.service.MenuService;
import com.oik.service.service.RoleMenuService;
import com.oik.service.service.RoleService;
import com.oik.util.redis.CacheClient;
import com.oik.util.redis.RedisConstants;
import com.oik.util.redis.RedisDeserialization;
import com.oik.util.redis.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@Service
@Slf4j
public class MenuServiceImpl extends MPJBaseServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private CacheClient cacheClient;
    @Resource
    private CacheService cacheService;
    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private RoleService roleService;

    @Override
    public Set<String> findUserPermissions(String username) {
        List<Menu> menus = getAll(username);
        List<Menu> menuList = menus.stream().filter(menu -> StrUtil.isNotBlank(menu.getPerms())).collect(Collectors.toList());
        Set<String> collect = menuList.stream().map(Menu::getPerms).collect(Collectors.toSet());
        System.out.println("JSONUtil.toJsonStr(collect) = " + JSONUtil.toJsonStr(collect));
        cacheClient.set(RedisConstants.USER_PERMISSION_CACHE_PREFIX + username, JSONUtil.toJsonStr(collect), 30L, TimeUnit.MINUTES);
        return collect;
    }


    @Override
    public List<Menu> getMenus() {
        String username = UserHolder.getUser().getUsername();
        return CacheClient.selectCacheByTemplate(
                () -> this.cacheService.getMenus(username),
                () -> getMenuTree(username));
    }


    public List<Menu> getAll(String username) {
        return selectJoinList(Menu.class, new MPJLambdaWrapper<Menu>()
                        .selectAll(Menu.class)
                        .leftJoin(RoleMenu.class, RoleMenu::getMenuId, Menu::getMenuId)
                        .leftJoin(Role.class, Role::getRoleId, RoleMenu::getRoleId)
                        .leftJoin(UserRole.class, UserRole::getRoleId, Role::getRoleId)
                        .leftJoin(User.class, User::getUserId, UserRole::getUserId)
                        .eq(User::getUsername, username)
                        .orderByAsc(Menu::getOrderNum)
//                .isNotNull(Menu::getPerms)
        );
    }

    @Override
    public List<Menu> getMenuTree(String username) {
        List<Menu> all = getAll(username);
        List<Menu> collect = all.stream()
                .filter(e -> StrUtil.isNotBlank(e.getPath()))
                .collect(Collectors.toList());
        List<Menu> father = collect.stream()
                .filter(e -> e.getParentId().equals("0"))
                .collect(Collectors.toList());
        Map<String, List<Menu>> map = collect.stream()
                .collect(Collectors.groupingBy(Menu::getParentId,
                        Collectors.mapping(Function.identity(), Collectors.toList())));
        father.forEach(e -> e.setChildren(map.get(e.getMenuId())));
        cacheClient.set(RedisConstants.USER_CONFIG_CACHE_MENU + username,
                JSON.toJSONString(father), 24L, TimeUnit.HOURS);
        return father;
    }

    @Override
    public Result<List<Menu>> getPermsByRoleId(String roleId) {
        MPJLambdaWrapper<Menu> wrapper = new MPJLambdaWrapper<Menu>()
                .selectAll(Menu.class)
                .leftJoin(RoleMenu.class, RoleMenu::getMenuId, Menu::getMenuId)
                .eq(RoleMenu::getRoleId, roleId);
        List<Menu> menus = selectJoinList(Menu.class, wrapper);
        return ResultUtil.getSuccess(menus);
    }

    @Override
    public Page<Menu> menus(Page page, Menu menu) {
        MPJLambdaWrapper<Menu> wrapper = new MPJLambdaWrapper<Menu>()
                .selectAll(Menu.class)
                .like(StringUtils.isNotEmpty(menu.getMenuName()), Menu::getMenuName, menu.getMenuName())
                .eq(Menu::getParentId, 0);
        Page<Menu> parent = (Page<Menu>) selectJoinListPage(page, Menu.class, wrapper);
        wrapper.clear();
        wrapper.selectAll(Menu.class).eq(Menu::getType, 0);
        List<Menu> menus = list(wrapper);
        System.out.println("JSON.toJSONString(menus) = " + JSON.toJSONString(menus));
        wrapper.clear();
        wrapper.selectAll(Menu.class).eq(Menu::getType, 1);
        List<Menu> perms = list(wrapper);
        Map<String, List<Menu>> permsMap = perms.stream()
                .collect(Collectors
                        .groupingBy(Menu::getParentId, Collectors
                                .mapping(Function.identity(), Collectors.toList())));
        menus.forEach(e -> e.setChildren(permsMap.get(e.getMenuId())));
        Map<String, List<Menu>> menusMap = menus.stream()
                .collect(Collectors
                        .groupingBy(Menu::getParentId, Collectors
                                .mapping(Function.identity(), Collectors.toList())));
        parent.getRecords().forEach(e -> e.setChildren(menusMap.get(e.getMenuId())));
        log.info(parent.getSize()+"============================");
        return parent;
    }


    @Override
    @Transactional
    public Menu addOrSet(Menu menu) {
        saveOrUpdate(menu);
        LambdaQueryWrapper<Role> lambda = new QueryWrapper<Role>().lambda();
        lambda.eq(Role::getRoleName, "管理员");
        Role one = roleService.getOne(lambda);
        roleMenuService.add(new RoleMenu(one.getRoleId(), menu.getMenuId()));
//        UserDTO user = UserHolder.getUser();
        Long deletes = cacheClient.deletes(RedisConstants.USER_PERMISSION_CACHE_PREFIX);
        Long deletes2 = cacheClient.deletes(RedisConstants.USER_CONFIG_CACHE_MENU);
        cacheClient.delete(RedisConstants.SYS_SELECT_MENU);
        log.info("redis删除" + RedisConstants.USER_PERMISSION_CACHE_PREFIX + "数据共" + deletes + "条");
        log.info("redis删除"+RedisConstants.USER_CONFIG_CACHE_MENU+"数据共"+deletes2+"条");
        return null;
    }

    @Override
    @Transactional
    public Object delete(String id) {
        removeById(id);
        remove(new LambdaQueryWrapper<Menu>().eq(Menu::getParentId, id));
        roleMenuService.remove(new QueryWrapper<RoleMenu>().lambda().eq(RoleMenu::getMenuId, id));
        Long deletes = cacheClient.deletes(RedisConstants.USER_PERMISSION_CACHE_PREFIX);
        Long deletes2 = cacheClient.deletes(RedisConstants.USER_CONFIG_CACHE_MENU);
        cacheClient.delete(RedisConstants.SYS_SELECT_MENU);
        log.info("redis删除" + RedisConstants.USER_PERMISSION_CACHE_PREFIX + "数据共" + deletes + "条");
        log.info("redis删除" + RedisConstants.USER_CONFIG_CACHE_MENU + "数据共" + deletes2 + "条");

        return null;
    }

    @Override
    public IPage<Menu> menusRedis(Page<Menu> page, Menu param) {
        RedisDeserialization<Menu> Deserialization = new RedisDeserialization<>(cacheClient);
        try {
            return Deserialization.queryWithPassThrough2(RedisConstants.SYS_SELECT_MENU, "", () -> (Page<Menu>) menus(page, param), 30L, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
