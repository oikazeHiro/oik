package com.oik.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.service.MPJJoinService;
import com.oik.dao.entity.User;
import com.oik.service.exception.MyException;
import com.oik.service.exception.Result;
import com.oik.util.dto.UserDTO;

import java.io.UnsupportedEncodingException;
import java.util.Map;

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

    Result<UserDTO> login(User user) throws MyException, UnsupportedEncodingException;

    UserDTO updateLoginTime(String username) throws MyException;

    Result register(User user);

    Result<Boolean> logout();

    Result<Page<User>> getUser(Page page, User user);

    Result<Map<String, Object>> index(String username);

    Boolean updateUser(User user);

    void removeUser(String userId);
}
