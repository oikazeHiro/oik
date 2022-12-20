package com.oik.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.oik.dao.entity.Greet;
import com.oik.dao.mapper.GreetMapper;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultUtil;
import com.oik.service.service.GreetService;
import com.oik.util.redis.CacheClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.concurrent.TimeUnit;

import static com.oik.util.redis.RedisConstants.SYS_GREET;
import static com.oik.util.redis.RedisConstants.SYS_GREET_LOCK;

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

    @Autowired
    private CacheClient cacheClient;

    @Override
    public Result getGreet(Page<Greet> page) {
        String value = cacheClient.getValue(SYS_GREET, "");
        Page<Greet> greetPage = null;
        if (StringUtils.isEmpty(value)) {
                try {
                    if (cacheClient.tryLock(SYS_GREET_LOCK)) {
                        greetPage = baseMapper.selectPage(page,
                                new QueryWrapper<Greet>().lambda().orderByAsc(Greet::getSort));
                        cacheClient.set(SYS_GREET, greetPage, 60L, TimeUnit.MINUTES);
                    } else {
                        try {
                            Thread.sleep(100L);
                            getGreet(page);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } finally {
                    cacheClient.unLock(SYS_GREET_LOCK);
                }
        } else {
            greetPage = JSONUtil.toBean(value, page.getClass());
        }
        return ResultUtil.getSuccess(greetPage);
    }
}
