package com.oik.service.service;

import com.oik.dao.entity.LoginLog;
import com.github.yulichang.base.service.MPJJoinService;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
public interface LoginLogService extends MPJJoinService<LoginLog> {
    void saveLoginLog (LoginLog loginLog);

    List<Map<String, Object>> findLastSevenDaysVisitCount(String username);
}
