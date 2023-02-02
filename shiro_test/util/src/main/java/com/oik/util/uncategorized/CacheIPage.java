package com.oik.util.uncategorized;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/2 14:16
 */
public interface CacheIPage<T> {
    Page<T> select() throws Exception;
}
