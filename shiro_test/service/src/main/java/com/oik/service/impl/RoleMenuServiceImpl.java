package com.oik.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oik.dao.entity.RoleMenu;
import com.oik.dao.mapper.RoleMenuMapper;
import com.oik.service.service.RoleMenuService;
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
public class RoleMenuServiceImpl extends MPJBaseServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public RoleMenu add(RoleMenu roleMenu) {
        RoleMenu one = getOne(new QueryWrapper<RoleMenu>().lambda()
                .eq(RoleMenu::getRoleId, roleMenu.getRoleId())
                .eq(RoleMenu::getMenuId, roleMenu.getMenuId()));
        if (one == null){
            save(roleMenu);
        }
        return roleMenu;
    }
}
