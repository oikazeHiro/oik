package com.oik.api.controller;

import com.oik.dao.entity.RoleMenu;
import com.oik.util.exception.Result;
import com.oik.util.exception.ResultUtil;
import com.oik.service.service.RoleMenuService;
import com.oik.util.redis.CacheClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.oik.util.redis.RedisConstants.USER_CONFIG_CACHE_MENU;
import static com.oik.util.redis.RedisConstants.USER_PERMISSION_CACHE_PREFIX;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@Slf4j
@RestController
@RequestMapping("")
public class RoleMenuController {

    @Autowired
    private CacheClient cacheClient;
    @Resource
    private RoleMenuService roleMenuService;

    @PostMapping("/role-menu")
    @RequiresPermissions("menu::add")
    public Result<RoleMenu> add(RoleMenu roleMenu) {
        Long deletes = cacheClient.deletes(USER_CONFIG_CACHE_MENU);
        Long deletes1 = cacheClient.deletes(USER_PERMISSION_CACHE_PREFIX);
        log.info("用户菜单remove" + deletes);
        log.info("用户权限remove" + deletes1);
        return ResultUtil.getSuccess(roleMenuService.add(roleMenu));
    }

    @DeleteMapping("/role-menu/{id}")
    @RequiresPermissions("menu:delete")
    public Result<Boolean> remove(@NotNull(message = "id is not null") @PathVariable("id") String id) {
        Long deletes = cacheClient.deletes(USER_CONFIG_CACHE_MENU);
        Long deletes1 = cacheClient.deletes(USER_PERMISSION_CACHE_PREFIX);
        log.info("用户菜单remove" + deletes);
        log.info("用户权限remove" + deletes1);
        return ResultUtil.getSuccess(roleMenuService.removeById(id));
    }

    @GetMapping("/role-menu/{id}")
    public Result<List<String>> getListBtRole(@PathVariable("id") String id) {
        return ResultUtil.getSuccess(roleMenuService.getListBtRole(id));
    }
}
