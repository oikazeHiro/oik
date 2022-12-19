package com.oik.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.service.MPJJoinService;
import com.oik.dao.entity.Greet;
import com.oik.service.exception.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oik
 * @since 2022-12-15
 */
public interface GreetService extends MPJJoinService<Greet> {

    Result getGreet(Page<Greet> page);
}
