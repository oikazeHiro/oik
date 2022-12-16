package com.oik.service.impl;

import com.oik.dao.entity.Greet;
import com.oik.dao.mapper.GreetMapper;
import com.oik.service.service.GreetService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oik
 * @since 2022-12-15
 */
@Service
public class GreetServiceImpl extends MPJBaseServiceImpl<GreetMapper, Greet> implements GreetService {

}
