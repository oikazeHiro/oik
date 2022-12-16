package com.oik.service.impl;

import com.oik.dao.entity.Dept;
import com.oik.dao.mapper.DeptMapper;
import com.oik.service.service.DeptService;
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
public class DeptServiceImpl extends MPJBaseServiceImpl<DeptMapper, Dept> implements DeptService {

}
