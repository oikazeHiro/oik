package com.oik.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.oik.dao.dto.AddRole;
import com.oik.dao.entity.Role;
import com.oik.dao.entity.User;
import com.oik.dao.entity.UserRole;
import com.oik.util.exception.Result;
import com.oik.util.exception.ResultUtil;
import com.oik.service.service.CacheService;
import com.oik.service.service.UserRoleService;
import com.oik.service.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.oik.util.redis.RedisConstants.USER_ROLE_CACHE_PREFIX;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@RestController
@RequestMapping("/")
public class UserRoleController {

    @Resource
    private CacheService cacheService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private UserService userService;

    @GetMapping("/roles/{username}")
    @RequiresPermissions("role:view")
    public Result<List<Role>> getRole(@PathVariable String username) {
        return userRoleService.getRole(username);
    }

    @PostMapping("/user-role")
    @RequiresPermissions("role:add")
    public Result<Boolean> add(@RequestBody UserRole userRole, @RequestParam String username) {
        cacheService.delete(USER_ROLE_CACHE_PREFIX + username);
        return ResultUtil.getSuccess(userRoleService.save(userRole));
    }

    @PostMapping("/user-role2")
    public Result<Boolean> add2(@RequestBody AddRole param) {
        userRoleService.addRole(param);
        return ResultUtil.getSuccess(true, "success");
    }

    @DeleteMapping("/user-role/{id}")
    @RequiresPermissions("role:delete")
    public Result<Boolean> remove(@PathVariable("id") String id, @RequestParam String username) {
        cacheService.delete(USER_ROLE_CACHE_PREFIX + username);
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        return ResultUtil.getSuccess(userRoleService.remove(new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getRoleId, id).eq(UserRole::getUserId, user.getUserId())));
    }
}
