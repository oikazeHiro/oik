package com.oik.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.oik.dao.entity.Role;
import com.oik.dao.entity.User;
import com.oik.dao.entity.UserRole;
import com.oik.dao.mapper.RoleMapper;
import com.oik.service.service.CacheService;
import com.oik.service.service.RoleService;
import com.oik.service.service.UserService;
import com.oik.util.redis.CacheClient;
import com.oik.util.redis.RedisConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class RoleServiceImpl extends MPJBaseServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private CacheClient cacheClient;
    @Resource
    private CacheService cacheService;
    @Resource
    private UserService userService;

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
                .eq(StringUtils.isNotEmpty(role.getStatus()), Role::getStatus, role.getStatus())
                .ge(role.getStartTime() != null, Role::getCreateTime, role.getStartTime())
                .le(role.getEndTime() != null, Role::getCreateTime, role.getEndTime());
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
        Map<String,Object> result = new HashMap<>();
        result.put("youRole",collect);
        result.put("allRole",all);
        return result;
    }


}
