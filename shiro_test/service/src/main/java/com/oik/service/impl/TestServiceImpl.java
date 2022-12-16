package com.oik.service.impl;

import com.oik.dao.entity.Test;
import com.oik.dao.mapper.TestMapper;
import com.oik.service.service.TestService;
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
public class TestServiceImpl extends MPJBaseServiceImpl<TestMapper, Test> implements TestService {

}
