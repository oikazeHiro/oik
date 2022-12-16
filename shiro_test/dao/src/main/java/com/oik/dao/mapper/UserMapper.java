package com.oik.dao.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.oik.dao.entity.User;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author oik
 * @since 2022-12-16
 */
public interface UserMapper extends MPJBaseMapper<User> {

    String findSubordinates(Long deptId);
}
