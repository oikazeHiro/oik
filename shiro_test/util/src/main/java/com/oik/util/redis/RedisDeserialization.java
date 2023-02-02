package com.oik.util.redis;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.util.uncategorized.CacheIPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.oik.util.redis.CacheClient.EXECUTOR_SERVICE;
import static com.oik.util.redis.RedisConstants.CACHE_NULL_TTL;
import static com.oik.util.redis.RedisConstants.LOCK_SHOP_KEY;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/2 13:57
 */
@Slf4j
@Component
public class RedisDeserialization<T> {

    private CacheClient cacheClient;

    public RedisDeserialization(CacheClient cacheClient) {
        this.cacheClient = cacheClient;
    }

    public IPage<T> queryWithPassThrough2(
            String keyPrefix, String id, CacheIPage<T> cacheIPage, Long time, TimeUnit timeUnit
    ) throws InterruptedException {
        String key = keyPrefix + id;
        // redis 查取缓存
        String json = cacheClient.getValue(key, "");
        log.info(json);
        // 判断缓存是否命中
        if (StrUtil.isNotBlank(json)) {
            return JSON.parseObject(json, new TypeReference<Page<T>>() {
            });
//            return JSONUtil.toBean(json, type);
        }
        //判断是否是空值
        if (json != null) {
            return null;
        }
        String lockKey = LOCK_SHOP_KEY + id;
        boolean lock = cacheClient.tryLock(lockKey);
        if (lock) {
            EXECUTOR_SERVICE.submit(() -> {
                try {
                    IPage<T> r = cacheIPage.select();
                    if (r == null) {
                        cacheClient.set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
                    }
                    cacheClient.set(key, r, time, timeUnit);
                } catch (Exception e) {
                    log.error(e.getMessage());
                } finally {
                    cacheClient.unLock(lockKey);
                }
            });
        } else {
            Thread.sleep(100L);
        }
        return queryWithPassThrough2(keyPrefix, id, cacheIPage, time, timeUnit);
    }
}
