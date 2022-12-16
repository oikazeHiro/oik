package com.oik.service.impl;

import com.oik.dao.entity.UserConfig;
import com.oik.dao.mapper.UserConfigMapper;
import com.oik.service.service.UserConfigService;
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
public class UserConfigServiceImpl extends MPJBaseServiceImpl<UserConfigMapper, UserConfig> implements UserConfigService {

}
