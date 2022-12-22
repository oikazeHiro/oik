package com.oik.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.oik.dao.entity.ChatMsg;
import com.oik.dao.mapper.ChatMsgMapper;
import com.oik.service.service.ChatMsgService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author oik
 * @since 2022-12-22
 */
@Service
public class ChatMsgServiceImpl extends MPJBaseServiceImpl<ChatMsgMapper, ChatMsg> implements ChatMsgService {

}
