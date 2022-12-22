package com.oik.api.netty.service;

import com.oik.api.netty.pojo.Message;
import io.netty.channel.ChannelHandlerContext;

public interface Read0Service {
    void operate(ChannelHandlerContext ctx, Message.Msg msg) throws Exception;
}
