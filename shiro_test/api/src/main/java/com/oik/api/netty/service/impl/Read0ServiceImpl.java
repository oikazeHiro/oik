package com.oik.api.netty.service.impl;

import com.oik.api.netty.pojo.Message;
import com.oik.api.netty.service.Read0Service;
import com.oik.api.netty.util.ChannelOperateUtil;
import com.oik.dao.entity.ChatMsg;
import com.oik.service.service.ChatMsgService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class Read0ServiceImpl implements Read0Service {

    @Resource
    private ChatMsgService chatMsgService;

    @Override
    public void operate(ChannelHandlerContext ctx, Message.Msg msg) {
        int number = msg.getDataType().getNumber();
        switch (number) {
            case 0:
                sendChatMsg(msg);
                break;
            case 1:
                sendChatMsgAll(msg);
                break;
            case 2:

                sendChatMsgByGroup(msg);
                break;
            case 3:
                doBindChannel(ctx, msg);
                break;
            default:
        }
    }

    public void sendChatMsg(Message.Msg msg) {
        Message.ChatMsg chatMsg = msg.getChatMsg();
        chatMsgService.sendChatMsg(new ChatMsg(chatMsg.getUserId(),
                chatMsg.getAcceptId(), chatMsg.getMessage()));
        String acceptId = chatMsg.getAcceptId();
        ChannelId channelId = ChannelOperateUtil.getChannelMap(acceptId);
        Channel channel = ChannelOperateUtil.findChannel(channelId);
        channel.writeAndFlush(msg);
    }

    public void sendChatMsgAll(Message.Msg msg) {
        ChannelOperateUtil.sendAll(msg);
    }

    public void sendChatMsgByGroup(Message.Msg msg) {
        Message.ChatMsgByGroup chatMsgByGroup = msg.getChatMsgByGroup();
    }

    public void doBindChannel(ChannelHandlerContext ctx, Message.Msg msg) {
        Message.BindChannel bindChannel = msg.getBindChannel();
        Channel channel = ctx.channel();
        ChannelOperateUtil.addChannelMap(bindChannel.getUserId(), channel.id());
    }

}
