package com.oik.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.oik.dao.entity.Role;
import com.oik.dao.entity.User;
import com.oik.dao.entity.UserRole;
import com.oik.dao.mapper.UserRoleMapper;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultUtil;
import com.oik.service.service.RoleService;
import com.oik.service.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@Service
public class UserRoleServiceImpl extends MPJBaseServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Resource
    private RoleService roleService;

    @Override
    public Result<List<Role>> getRole(String username) {
        MPJLambdaWrapper<Role> wrapper = new MPJLambdaWrapper<Role>()
                .selectAll(Role.class)
                .leftJoin(UserRole.class, UserRole::getRoleId, Role::getRoleId)
                .leftJoin(User.class, User::getUserId, UserRole::getUserId)
                .eq(User::getUsername, username);
        List<Role> roles = roleService.selectJoinList(Role.class, wrapper);
        return ResultUtil.getSuccess(roles);
    }
}
