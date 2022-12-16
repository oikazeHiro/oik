package com.oik.service.impl;

import com.oik.dao.entity.Job;
import com.oik.dao.mapper.JobMapper;
import com.oik.service.service.JobService;
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
public class JobServiceImpl extends MPJBaseServiceImpl<JobMapper, Job> implements JobService {

}
