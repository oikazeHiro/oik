package com.oik.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.oik.dao.dto.expandMsgTest;
import com.oik.dao.entity.ChatMsg;
import com.oik.dao.mapper.ChatMsgMapper;
import com.oik.service.service.ChatMsgService;
import com.oik.util.channelUitl.MessageUtil;
import com.oik.util.exception.Result;
import com.oik.util.exception.ResultUtil;
import com.oik.util.redis.UserHolder;
import com.oik.util.str.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private MessageUtil messageUtil;
    @Resource
    private JsonUtil jsonUtil;

    @Override
    public Result<List<ChatMsg>> getMsg() {
        return null;
    }

    @Override
    @Transactional
    public ChatMsg saveChatMsg(ChatMsg msg) {
        this.save(msg);
        return msg;
    }

    @Override
    public Page<ChatMsg> getChatMsg(Page<ChatMsg> page, ChatMsg chatMsg) {
        MPJLambdaWrapper<ChatMsg> mapper = new MPJLambdaWrapper<>();
        mapper.selectAll(ChatMsg.class)
                .or(e -> e.eq(ChatMsg::getSendId,chatMsg.getSendId()).eq(ChatMsg::getAcceptId,chatMsg.getAcceptId()))
                .or(e -> e.eq(ChatMsg::getSendId,chatMsg.getAcceptId()).eq(ChatMsg::getAcceptId,chatMsg.getSendId()))
                .eq(ChatMsg::getCode,1)
                .orderByDesc(ChatMsg::getCreateTime);
        Page<ChatMsg> chatMsgPage = (Page<ChatMsg>) selectJoinListPage(page, ChatMsg.class, mapper);
        chatMsgPage.setRecords(chatMsgPage.getRecords().stream()
                .sorted(Comparator.comparing(ChatMsg::getCreateTime).reversed())
                .collect(Collectors.toList()));
        return chatMsgPage;
    }

    @Override
    @Transactional
    public Result sendPrivateMsg(ChatMsg chatMsg) {
        boolean save = save(chatMsg);
        messageUtil.sendMessage(chatMsg.getAcceptId(), jsonUtil.fastJsonSerializer(chatMsg));
        messageUtil.sendMessage(chatMsg.getSendId(), jsonUtil.fastJsonSerializer(chatMsg));
        return ResultUtil.getSuccess(save);
    }

    @Override
    @Transactional
    public void sendSysMsg(ChatMsg chatMsg) {
//        save(chatMsg);
        messageUtil.sendNotice(jsonUtil.fastJsonSerializer(chatMsg));
    }

    @Override
    public void sendExpandMsgTestToAllUser() {
        ArrayList<expandMsgTest> list = new ArrayList<>();
        list.add(new expandMsgTest("推送用户","全部"));
        list.add(new expandMsgTest("推送类型","测试数据"));
        ChatMsg chatMsg = new ChatMsg(
                6,"小程序模拟推送", UserHolder.getUser().getUserId(),"",jsonUtil.fastJsonSerializer(list)
        );
        messageUtil.sendNotice(jsonUtil.fastJsonSerializer(chatMsg));
    }

    @Override
    public void sendExpandMsgTestToOneUser(String userId) {
        ArrayList<expandMsgTest> list = new ArrayList<>();
        list.add(new expandMsgTest("推送用户",userId));
        list.add(new expandMsgTest("推送类型","测试数据"));
        ChatMsg chatMsg = new ChatMsg(
                6,"小程序模拟推送", UserHolder.getUser().getUserId(),userId,jsonUtil.fastJsonSerializer(list)
        );
        messageUtil.sendMessage(userId,jsonUtil.fastJsonSerializer(chatMsg));
    }

}
