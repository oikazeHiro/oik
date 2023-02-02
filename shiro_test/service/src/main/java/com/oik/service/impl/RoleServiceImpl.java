package com.oik.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.oik.dao.entity.Role;
import com.oik.dao.entity.RoleMenu;
import com.oik.dao.entity.User;
import com.oik.dao.entity.UserRole;
import com.oik.dao.mapper.RoleMapper;
import com.oik.service.service.CacheService;
import com.oik.service.service.RoleMenuService;
import com.oik.service.service.RoleService;
import com.oik.service.service.UserService;
import com.oik.util.redis.CacheClient;
import com.oik.util.redis.RedisConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.oik.util.redis.RedisConstants.USER_CONFIG_CACHE_MENU;
import static com.oik.util.redis.RedisConstants.USER_PERMISSION_CACHE_PREFIX;

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
public class RoleServiceImpl extends MPJBaseServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private CacheClient cacheClient;
    @Resource
    private CacheService cacheService;
    @Resource
    private UserService userService;
    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public List<Role> findUserRole(String username) {
        List<Role> roles = selectJoinList(Role.class,
                new MPJLambdaWrapper<Role>()
                        .selectAll(Role.class)
                        .leftJoin(UserRole.class, UserRole::getRoleId, Role::getRoleId)
                        .leftJoin(User.class, User::getUserId, UserRole::getUserId)
                        .eq(User::getUsername, username));
        cacheClient.set(RedisConstants.USER_ROLE_CACHE_PREFIX + username, JSONUtil.toJsonStr(roles));
        return roles;
    }

    @Override
    public IPage<Role> getRoles(Page<Role> page, Role role) {
        MPJLambdaWrapper<Role> wrapper = new MPJLambdaWrapper<Role>()
                .selectAll(Role.class)
                .like(StringUtils.isNotEmpty(role.getRoleName()), Role::getRoleName, role.getRoleName())
                .like(StringUtils.isNotEmpty(role.getRemark()), Role::getRemark, role.getRemark())
                .eq(role.getStatus() != null, Role::getStatus, role.getStatus());
        return selectJoinListPage(page, Role.class, wrapper);
    }

    @Override
    public List<Role> getUserRole(String userID) {
        MPJLambdaWrapper<Role> wrapper = new MPJLambdaWrapper<Role>()
                .selectAll(Role.class)
                .leftJoin(UserRole.class,UserRole::getRoleId,Role::getRoleId)
                .eq(UserRole::getUserId,userID);
        return selectJoinList(Role.class,wrapper);
    }

    @Override
    public Map<String, Object> viewAddRole(String userId) {
        User byId = userService.getById(userId);
        List<Role> roleList = CacheClient.selectCacheByTemplate(
                () -> this.cacheService.getRoles(byId.getUsername()),
                () -> this.getUserRole(userId));
        List<String> collect = roleList.stream().map(Role::getRoleId).collect(Collectors.toList());
        List<Role> all = list();
        Map<String, Object> result = new HashMap<>();
        result.put("youRole", collect);
        result.put("allRole", all);
        return result;
    }

    @Override
    @Transactional
    public Boolean saveRole(Role role) {
        List<String> perms = role.getPerms();
        saveOrUpdate(role);
        savePerms(perms, role.getRoleId());
        return true;
    }

    @Transactional
    public void savePerms(List<String> perms, String IdVal) {
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getRoleId, IdVal);
        roleMenuService.remove(wrapper);
        for (String s : perms) {
            roleMenuService.save(new RoleMenu(IdVal, s));
        }
        Long deletes = cacheClient.deletes(USER_CONFIG_CACHE_MENU);
        Long deletes1 = cacheClient.deletes(USER_PERMISSION_CACHE_PREFIX);
        log.info("用户菜单remove" + deletes);
        log.info("用户权限remove" + deletes1);
    }

}
