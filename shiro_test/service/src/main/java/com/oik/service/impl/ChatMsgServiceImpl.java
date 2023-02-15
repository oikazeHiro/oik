package com.oik.service.impl;

import com.alibaba.fastjson2.JSON;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.oik.dao.entity.ChatMsg;
import com.oik.dao.mapper.ChatMsgMapper;
import com.oik.service.exception.Result;
import com.oik.service.service.ChatMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author oik
 * @since 2022-12-22
 */
@Service
@Slf4j
public class ChatMsgServiceImpl extends MPJBaseServiceImpl<ChatMsgMapper, ChatMsg> implements ChatMsgService {

    @Override
    public Result<List<ChatMsg>> getMsg() {
        return null;
    }

    @Override
    @Transactional
    public ChatMsg sendChatMsg(ChatMsg msg) {
        log.info("JSON.toJSONString(msg) = " + JSON.toJSONString(msg));
        this.save(msg);
        return msg;
    }

}
