package com.oik.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.service.MPJJoinService;
import com.oik.dao.entity.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
public interface RoleService extends MPJJoinService<Role> {

    List<Role> findUserRole(String username);

    IPage<Role> getRoles(Page<Role> page, Role role);

    List<Role> getUserRole(String userID);
}
