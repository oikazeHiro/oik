package com.oik.dao.mapper;

import com.oik.dao.entity.LoginLog;
import com.github.yulichang.base.MPJBaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
public interface LoginLogMapper extends MPJBaseMapper<LoginLog> {

    List<Map<String, Object>> findLastSevenDaysVisitCount(String username);
}
