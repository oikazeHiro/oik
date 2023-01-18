package com.oik.service.service;

import com.github.yulichang.base.service.MPJJoinService;
import com.oik.dao.entity.ChatMsg;
import com.oik.service.exception.Result;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author oik
 * @since 2022-12-22
 */
public interface ChatMsgService extends MPJJoinService<ChatMsg> {

    Result<List<ChatMsg>> getMsg();

    ChatMsg sendChatMsg(ChatMsg msg);
}
