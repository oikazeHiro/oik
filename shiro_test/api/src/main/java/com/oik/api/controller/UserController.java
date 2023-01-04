package com.oik.api.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.dao.entity.User;
import com.oik.service.exception.MyException;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultEnum;
import com.oik.service.exception.ResultUtil;
import com.oik.service.service.UserService;
import com.oik.util.dto.LoginDto;
import com.oik.util.dto.UserDTO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        try {
            return userService.login(BeanUtil.copyProperties(loginDto, User.class));
        } catch (MyException e) {
            throw new MyException(401, e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/test")
//    @RequiresRoles({"管理员"})
    @RequiresPermissions("user:view")
    public String test() {
        String s = "11111";
        System.out.println("s = " + s);
        return s;
    }

    @PostMapping("/register")
    @RequiresPermissions("user:add")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/logout")
    public Result logout() {
        return userService.logout();
    }

    @GetMapping("/users")
    @RequiresPermissions("user:view")
    public Result getUser(Page page,User user) {
        return userService.getUser(page,user);
    }

    @GetMapping("/index/{username}")
    public Result index(@NotNull(message = "username is not null") @PathVariable("username") String username){
        return userService.index(username);
    }

    @PutMapping("/update")
    @RequiresPermissions("user:update")
    public Result resetUser(@RequestBody User user) {
        return ResultUtil.getSuccess(userService.updateById(user));
    }

    @DeleteMapping("/delete/{userId}")
    @RequiresPermissions("user:delete")
    public Result delete(@NotNull(message = "userId is not null")@PathVariable("userId") Long userId){
        if(userId == 1) throw new MyException(ResultEnum.UNAUTHORIZED_ERROR);
        return ResultUtil.getSuccess(userService.removeById(userId));
    }
}
