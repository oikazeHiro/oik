package com.oik.api.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.dao.entity.User;
import com.oik.service.exception.MyException;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultEnum;
import com.oik.service.exception.ResultUtil;
import com.oik.service.service.UserService;
import com.oik.util.dto.LoginDto;
import com.oik.util.dto.UserDTO;
import com.oik.util.redis.UserHolder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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
    public Result<UserDTO> login(@RequestBody LoginDto loginDto) {
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
    public Result<UserDTO> register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/logout")
    public Result<Boolean> logout() {
        return userService.logout();
    }

    @GetMapping("/users")
    @RequiresPermissions("user:view")
    public Result<Page<User>> getUser(Page<User> page, User user) {
        return userService.getUser(page, user);
    }

    @GetMapping("/index/{username}")
    public Result<Map<String, Object>> index(@NotNull(message = "username is not null") @PathVariable("username") String username) {
        return userService.index(username);
    }

    @PutMapping("/resetUser")
    @RequiresPermissions("user:update")
    public Result<Boolean> resetUser(@RequestBody User user) {
        user.setPassword(user.getPassword());
        return ResultUtil.getSuccess(userService.updateById(user));
    }

    @PutMapping("/update")
    @RequiresPermissions("user:update")
    public Result<Boolean> update(@RequestBody User user) {
        return ResultUtil.getSuccess(userService.updateUser(user));
    }

    @DeleteMapping("/delete/{userId}")
    @RequiresPermissions("user:delete")
    public Result<Boolean> delete(@NotNull(message = "userId is not null") @PathVariable("userId") String userId) {
        if (userId.equals("1")) throw new MyException(ResultEnum.UNAUTHORIZED_ERROR);
        userService.removeUser(userId);
        return ResultUtil.getSuccess();
    }

    @GetMapping("/get-user")
    public Result<UserDTO> getUserDto() {
        return ResultUtil.getSuccess(UserHolder.getUser());
    }

    @GetMapping("/get-user-list/{userId}")
    public Result<List<User>> getUserList(@PathVariable("userId") String userId) {
        LambdaQueryWrapper<User> Wrapper = new LambdaQueryWrapper<>();
        Wrapper.ne(User::getUserId, userId);
        List<User> list = userService.list(Wrapper);
        return ResultUtil.getSuccess(list);
    }
}
