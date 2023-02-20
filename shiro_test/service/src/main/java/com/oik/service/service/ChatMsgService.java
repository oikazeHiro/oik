package com.oik.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.service.MPJJoinService;
import com.oik.dao.entity.ChatMsg;
import com.oik.util.exception.Result;

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

    ChatMsg saveChatMsg(ChatMsg msg);

    Page<ChatMsg> getChatMsg(Page<ChatMsg> page, ChatMsg chatMsg);

    Result sendPrivateMsg(ChatMsg chatMsg);

    void sendSysMsg(ChatMsg chatMsg);

    void sendExpandMsgTestToAllUser();

    void sendExpandMsgTestToOneUser( String userId);
}
