package com.oik.api.netty.handler;

import com.oik.api.netty.pojo.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,MyDataInfo.MyMessage myMessage) throws Exception {

    }
}
