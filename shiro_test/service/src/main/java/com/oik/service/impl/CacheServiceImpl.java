package com.oik.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.oik.dao.entity.Menu;
import com.oik.dao.entity.Role;
import com.oik.dao.entity.User;
import com.oik.service.service.CacheService;
import com.oik.util.redis.CacheClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.oik.util.redis.RedisConstants.*;

@Service
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Autowired
    private CacheClient cacheClient;
    @Override
    public User getUser(String username) throws Exception {
        return null;
    }

    @Override
    public List<Role> getRoles(String username) throws Exception {
        String value = cacheClient.getValue(USER_ROLE_CACHE_PREFIX, username);
        if (StrUtil.isBlank(value)) {
            return null;
        }
        JSONArray jsonArray = JSONUtil.parseArray(value);
        return JSONUtil.toList(jsonArray, Role.class);
    }

    @Override
    public String getUserSubordinates(Long deptId) throws Exception {
        return null;
    }

    @Override
    public Set<String> getPermissions(String username) {
        String value = cacheClient.getValue(USER_PERMISSION_CACHE_PREFIX, username);
        log.info(USER_PERMISSION_CACHE_PREFIX+username);
        if (StrUtil.isBlank(value)) {
            return null;
        }
        JSONArray jsonArray = JSONUtil.parseArray(value);
        List<String> strings = JSONUtil.toList(jsonArray, String.class);
        return new HashSet<String>(strings);
    }

    @Override
    public List<Menu> getMenus(String username) {
        String value = cacheClient.getValue(USER_CONFIG_CACHE_MENU, username);
        log.info(USER_CONFIG_CACHE_MENU+username);
        if (StrUtil.isBlank(value)) {
            return null;
        }
        JSONArray jsonArray = JSONUtil.parseArray(value);
        return JSONUtil.toList(jsonArray, Menu.class);
    }
}
