package com.oik.api.netty.handler;

import com.oik.api.netty.pojo.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<Message.Msg> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message.Msg msg) throws Exception {

    }
}
