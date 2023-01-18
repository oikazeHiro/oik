package com.oik.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.service.MPJJoinService;
import com.oik.dao.entity.Dept;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
public interface DeptService extends MPJJoinService<Dept> {


    Page<Dept> dept(Page<Dept> page, Dept dept);

    List<Dept> getALLDepth();
}
