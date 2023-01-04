package com.oik.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.oik.dao.entity.*;
import com.oik.dao.mapper.MenuMapper;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultUtil;
import com.oik.service.service.CacheService;
import com.oik.service.service.MenuService;
import com.oik.util.redis.CacheClient;
import com.oik.util.redis.RedisConstants;
import com.oik.util.redis.UserHolder;
import org.springframework.stereotype.Service;

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
public class MenuServiceImpl extends MPJBaseServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private CacheClient cacheClient;
    @Resource
    private CacheService cacheService;

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
                .filter(e -> e.getParentId() == 0)
                .collect(Collectors.toList());
        Map<Long, List<Menu>> map = collect.stream()
                .collect(Collectors.groupingBy(Menu::getParentId,
                        Collectors.mapping(Function.identity(), Collectors.toList())));
        father.forEach(e -> e.setChildren(map.get(e.getMenuId())));
        cacheClient.set(RedisConstants.USER_CONFIG_CACHE_MENU + username,
                JSON.toJSONString(father), 24L, TimeUnit.HOURS);
        return father;
    }

    @Override
    public Result getPermsByRoleId(Long roleId) {
        MPJLambdaWrapper<Menu> wrapper = new MPJLambdaWrapper<Menu>()
                .selectAll(Menu.class)
                .leftJoin(RoleMenu.class, RoleMenu::getMenuId, Menu::getMenuId)
                .eq(RoleMenu::getRoleId, roleId);
        List<Menu> menus = selectJoinList(Menu.class, wrapper);
        return ResultUtil.getSuccess(menus);
    }
}
