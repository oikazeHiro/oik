package com.oik.api.controller;

import com.oik.dao.entity.RoleMenu;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultUtil;
import com.oik.service.service.RoleMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {

    @Resource
    private RoleMenuService roleMenuService;

    @PostMapping("/role-menu")
    public Result add(RoleMenu roleMenu) {
        return ResultUtil.getSuccess(roleMenuService.save(roleMenu));
    }

    @DeleteMapping("/role-menu/{id}")
    public Result remove(@NotNull(message = "id is not null") @PathVariable("id") Long id) {
        return ResultUtil.getSuccess(roleMenuService.removeById(id));
    }
}
