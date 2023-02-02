package com.oik.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.oik.dao.dto.AddRole;
import com.oik.dao.entity.Role;
import com.oik.dao.entity.User;
import com.oik.dao.entity.UserRole;
import com.oik.dao.mapper.UserRoleMapper;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultUtil;
import com.oik.service.service.CacheService;
import com.oik.service.service.RoleService;
import com.oik.service.service.UserRoleService;
import com.oik.service.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.oik.util.redis.RedisConstants.USER_ROLE_CACHE_PREFIX;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@Service
public class UserRoleServiceImpl extends MPJBaseServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Resource
    private RoleService roleService;

    @Resource
    private CacheService cacheService;

    @Resource
    private UserService userService;


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

    @Override
    @Transactional
    public boolean addRole(AddRole param) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, param.getUserId());
        remove(wrapper);
        UserRole userRole = new UserRole();
        userRole.setUserId(param.getUserId());
        for (String s : param.getYouRole()) {
            userRole.setRoleId(s);
            save(userRole);
        }
        User user = userService.getById(param.getUserId());
        cacheService.delete(USER_ROLE_CACHE_PREFIX + user.getUsername());
        return true;
    }
}
