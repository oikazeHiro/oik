package com.oik.service.service;

import com.github.yulichang.base.service.MPJJoinService;
import com.oik.dao.entity.RoleMenu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
public interface RoleMenuService extends MPJJoinService<RoleMenu> {

    RoleMenu add(RoleMenu roleMenu);

    List<String> getListBtRole(String id);
}
