package com.oik.api.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.dao.entity.Role;
import com.oik.util.exception.Result;
import com.oik.util.exception.ResultUtil;
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
    public Result<IPage<Role>> getRoles(Page<Role> page, Role role) {
        IPage<Role> roles = roleService.getRoles(page, role);
        return ResultUtil.getSuccess(roles);
    }

    @GetMapping("/role-user/{userId}")
    public Result getURoleByUserId(@PathVariable("userId") String userId){
        return ResultUtil.getSuccess(roleService.viewAddRole(userId));
    }

    @PostMapping("/role")
    @RequiresPermissions("role:add")
    public Result<Boolean> add(@RequestBody Role role) {
        System.out.println("JSON.toJSONString(role) = " + JSON.toJSONString(role));
        return ResultUtil.getSuccess(roleService.saveRole(role));
    }

    @PutMapping("/role")
    @RequiresPermissions("role:update")
    public Result<Boolean> update(@RequestBody Role role) {
        return ResultUtil.getSuccess(roleService.saveRole(role));
    }

    @DeleteMapping("/role/{id}")
    @RequiresPermissions("role:delete")
    public Result<Boolean> delete(@PathVariable("id") String id) {
        roleService.removeRole(id);
        return ResultUtil.getSuccess();
    }


}
