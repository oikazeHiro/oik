package com.oik.service.service;

import com.oik.dao.entity.Menu;
import com.oik.dao.entity.Role;
import com.oik.dao.entity.User;
import com.oik.dao.entity.UserConfig;

import java.util.List;
import java.util.Set;

public interface CacheService {
    /**
     * 从缓存中获取用户
     *
     * @param username 用户名
     * @return User
     */
    User getUser(String username) throws Exception;

    /**
     * 从缓存中获取用户角色
     *
     * @param username 用户名
     * @return 角色集
     */
    List<Role> getRoles(String username) throws Exception;


    String getUserSubordinates(Long deptId) throws Exception;

    Set<String> getPermissions(String username);

    List<Menu> getMenus(String username);
}
