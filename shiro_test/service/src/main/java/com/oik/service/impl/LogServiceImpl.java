package com.oik.service.impl;

import com.oik.dao.entity.Log;
import com.oik.dao.mapper.LogMapper;
import com.oik.service.service.LogService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@Service
public class LogServiceImpl extends MPJBaseServiceImpl<LogMapper, Log> implements LogService {

}
