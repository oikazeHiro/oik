package com.oik.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oik.dao.entity.Menu;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultUtil;
import com.oik.service.service.CacheService;
import com.oik.service.service.MenuService;
import com.oik.util.redis.CacheClient;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@RestController
@RequestMapping("")
public class MenuController {

    @Resource
    private CacheService cacheService;
    @Resource
    private MenuService menuService;
    @GetMapping("/getMenus")
    public Result getMenus() {
        List<Menu> collect = menuService.getMenus();
        return ResultUtil.getSuccess(collect, (long) collect.size());
    }

    @GetMapping("/menus/{username}")
    @RequiresPermissions("menu:view")
    public Result menus(@PathVariable("username") String username) {
        List<Menu> permissionList = CacheClient.selectCacheByTemplate(
                () -> cacheService.getMenus(username),
                () -> menuService.getMenuTree(username));
        return ResultUtil.getSuccess(permissionList);
    }


    @PostMapping("/menu")
    @RequiresPermissions("menu:add")
    public Result add(@RequestBody Menu menu) {
        return ResultUtil.getSuccess(menuService.save(menu));
    }

    @PutMapping("/menu")
    @RequiresPermissions("menu:update")
    public Result set(@RequestBody Menu menu) {
        return ResultUtil.getSuccess(menuService.updateById(menu));
    }

    @DeleteMapping("/menu/{id}")
    @RequiresPermissions("menu:delete")
    public Result remove(@PathVariable("id") Long id) {
        return ResultUtil.getSuccess(menuService.removeById(id));
    }


    @GetMapping("/perms/{roleId}")
    @RequiresPermissions("menu:view")
    public Result getPermsByRoleId(@PathVariable("roleId") Long roleId){
        return menuService.getPermsByRoleId(roleId);
    }
    @GetMapping("/perms")
    @RequiresPermissions("menu:view")
    public Result getPerms(){
        return ResultUtil.getSuccess(menuService.list(new QueryWrapper<Menu>().lambda().isNotNull(Menu::getPerms)));
    }

}
