package com.oik.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.dao.entity.User;
import com.github.yulichang.base.service.MPJJoinService;
import com.oik.service.exception.MyException;
import com.oik.service.exception.Result;
import com.oik.util.dto.UserDTO;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
public interface UserService extends MPJJoinService<User> {

    String findSubordinates(Long deptId);

    User findByName(String username);

    Result login(User user) throws MyException, UnsupportedEncodingException;

    UserDTO updateLoginTime(String username) throws MyException;

    Result register(User user);

    Result logout();

    Result getUser(Page page, User user);

    Result index(String username);
}
