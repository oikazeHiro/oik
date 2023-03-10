package com.oik.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.service.MPJJoinService;
import com.oik.dao.entity.Menu;
import com.oik.util.exception.Result;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
public interface MenuService extends MPJJoinService<Menu> {

    Set<String> findUserPermissions(String username);

    List<Menu> getMenus();

    List<Menu> getMenuTree(String username);

    Result<List<Menu>> getPermsByRoleId(String roleId);

    IPage<Menu> menus(Page<Menu> page, Menu menu);

    Menu addOrSet(Menu menu);

    Object delete(String id);

    IPage<Menu> menusRedis(Page<Menu> page, Menu param);
}
