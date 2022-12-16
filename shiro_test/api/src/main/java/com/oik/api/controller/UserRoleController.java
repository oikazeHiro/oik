package com.oik.api.controller;

import com.oik.service.exception.Result;
import com.oik.service.service.UserRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/")
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    @GetMapping("/roles/{username}")
    public Result getRole(String username){
        return userRoleService.getRole(username);
    }
}
