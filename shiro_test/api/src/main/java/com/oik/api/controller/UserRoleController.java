package com.oik.api.controller;

import com.oik.dao.entity.UserRole;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultUtil;
import com.oik.service.service.CacheService;
import com.oik.service.service.UserRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @GetMapping("/roles/{username}")
    @RequiresPermissions("role:view")
    public Result getRole(@PathVariable String username) {
        return userRoleService.getRole(username);
    }

    @PostMapping("/user-role")
    @RequiresPermissions("role:add")
    public Result add(@RequestBody UserRole userRole, @RequestParam String username) {
        cacheService.delete(USER_ROLE_CACHE_PREFIX + username);
        return ResultUtil.getSuccess(userRoleService.save(userRole));
    }

    @DeleteMapping("/user-role/{id}")
    @RequiresPermissions("role:delete")
    public Result remove(@PathVariable("id") String id, @RequestParam String username) {
        cacheService.delete(USER_ROLE_CACHE_PREFIX + username);
        return ResultUtil.getSuccess(userRoleService.removeById(id));
    }
}
