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
import com.oik.service.service.RoleService;
import com.oik.util.redis.CacheClient;
import com.oik.util.redis.RedisConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        return null;
    }
}
