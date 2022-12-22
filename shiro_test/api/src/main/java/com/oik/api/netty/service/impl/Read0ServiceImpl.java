package com.oik.api.netty.service.impl;

import com.oik.api.netty.pojo.Message;
import com.oik.api.netty.service.Read0Service;
import com.oik.api.netty.util.ChannelOperateUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import org.springframework.transaction.annotation.Transactional;


public class Read0ServiceImpl implements Read0Service {
    @Override
    public void operate(ChannelHandlerContext ctx, Message.Msg msg) throws Exception {
        int number = msg.getDataType().getNumber();
        switch (number) {
            case 0:
                Message.ChatMsg chatMsg = msg.getChatMsg();
                sendChatMsg(ctx, chatMsg);
                break;
            case 1:
                Message.ChatMsgAll chatMsgAll = msg.getChatMsgAll();
                sendChatMsgAll(ctx, chatMsgAll);
                break;
            case 2:
                Message.ChatMsgByGroup chatMsgByGroup = msg.getChatMsgByGroup();
                sendChatMsgByGroup(ctx, chatMsgByGroup);
                break;
            case 3:
                Message.BindChannel bindChannel = msg.getBindChannel();
                doBindChannel(ctx, bindChannel);
                break;
            default:
        }
    }

    @Transactional
    public void sendChatMsg(ChannelHandlerContext ctx, Message.ChatMsg chatMsg) {
        String acceptId = chatMsg.getAcceptId();
        ChannelId channelId = ChannelOperateUtil.getChannelMap(acceptId);
        Channel channel = ChannelOperateUtil.findChannel(channelId);
        channel.writeAndFlush(chatMsg);
    }

    public void sendChatMsgAll(ChannelHandlerContext ctx, Message.ChatMsgAll chatMsgAll) {
    }

    public void sendChatMsgByGroup(ChannelHandlerContext ctx, Message.ChatMsgByGroup chatMsgByGroup) {
    }

    public void doBindChannel(ChannelHandlerContext ctx, Message.BindChannel bindChannel) {
    }

}
