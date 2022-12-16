package com.oik.service.impl;

import com.oik.dao.entity.LoginLog;
import com.oik.dao.mapper.LoginLogMapper;
import com.oik.service.service.LoginLogService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.oik.util.uncategorized.HttpContextUtil;
import com.oik.util.uncategorized.IPUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LoginLogServiceImpl extends MPJBaseServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
    @Override
    @Transactional
    public void saveLoginLog(LoginLog loginLog){
        loginLog.setLoginTime(LocalDateTime.now());
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);
        System.out.println("ip = " + ip);
        loginLog.setIp(ip);
        Map<String,String> cityInfo = IPUtil.getCityInfo(ip);
        loginLog.setLocation(cityInfo.get("city"));
        this.save(loginLog);
    }

    @Override
    public List<Map<String, Object>> findLastSevenDaysVisitCount(String username) {
        return this.baseMapper.findLastSevenDaysVisitCount(username);
    }

}
