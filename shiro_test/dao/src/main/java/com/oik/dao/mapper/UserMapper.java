package com.oik.dao.mapper;

import com.oik.dao.entity.User;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
public interface UserMapper extends MPJBaseMapper<User> {

    String findSubordinates(Long deptId);
}
