package com.oik.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.dao.entity.Role;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultUtil;
import com.oik.service.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/roles")
    @RequiresPermissions("role:view")
    public Result getRoles(Page<Role> page, Role role){
        IPage<Role> roles = roleService.getRoles(page, role);
        return ResultUtil.getSuccess(roles);
    }

    @PostMapping("/role")
    @RequiresPermissions("role:add")
    public Result add(@RequestBody Role role){
        return ResultUtil.getSuccess(roleService.save(role));
    }

    @PutMapping("/role")
    @RequiresPermissions("role:update")
    public Result update(@RequestBody Role role){
        return ResultUtil.getSuccess(roleService.save(role));
    }

    @DeleteMapping("/role/{id}")
    @RequiresPermissions("role:delete")
    public Result delete(@PathVariable("id") Long id){
        return ResultUtil.getSuccess(roleService.removeById(id));
    }

}
