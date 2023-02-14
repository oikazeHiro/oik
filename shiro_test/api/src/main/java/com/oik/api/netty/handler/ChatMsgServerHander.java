package com.oik.api.netty.handler;

import com.oik.api.netty.service.ChatMsgNettyService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/14 9:29
 */
@Slf4j
public class ChatMsgServerHander extends SimpleChannelInboundHandler<Object> {

    @Resource
    private ChatMsgNettyService chatMsgNettyService;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if (o instanceof FullHttpRequest) {
            FullHttpRequest httpRequest = (FullHttpRequest) o;
        } else if (o instanceof WebSocketFrame) {
            WebSocketFrame webSocketFrame = (WebSocketFrame) o;
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        log.info("ip:" + ctx.channel().remoteAddress() + "链接" + ctx.channel().localAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("————客户端与服务端连接断开————");
        chatMsgNettyService.clearSession(ctx.channel());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getMessage());
        ctx.close();
    }


}
