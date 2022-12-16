package com.oik.service.impl;

import com.oik.dao.entity.JobLog;
import com.oik.dao.mapper.JobLogMapper;
import com.oik.service.service.JobLogService;
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
public class JobLogServiceImpl extends MPJBaseServiceImpl<JobLogMapper, JobLog> implements JobLogService {

}
