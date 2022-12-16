package com.oik.service.service;

import com.oik.dao.entity.UserRole;
import com.github.yulichang.base.service.MPJJoinService;
import com.oik.service.exception.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
public interface UserRoleService extends MPJJoinService<UserRole> {

    Result getRole(String username);
}
