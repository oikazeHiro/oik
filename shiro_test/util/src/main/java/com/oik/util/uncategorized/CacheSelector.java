package com.oik.util.uncategorized;

public interface CacheSelector<T> {
    T select() throws Exception;
}
