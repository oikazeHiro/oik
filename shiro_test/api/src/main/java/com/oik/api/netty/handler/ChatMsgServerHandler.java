package com.oik.api.netty.handler;

import com.oik.api.netty.service.ChatMsgNettyService;
import com.oik.api.netty.service.impl.ChatMsgNettyServiceImpl;
import com.oik.util.application.SpringContextUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 15093
 * @description
 * @date 2023/2/14 9:29
 */
@Slf4j
@ChannelHandler.Sharable
public class ChatMsgServerHandler extends SimpleChannelInboundHandler<Object> {

    private ChatMsgNettyService chatMsgNettyService = SpringContextUtil.getBean(ChatMsgNettyServiceImpl.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if (o instanceof FullHttpRequest) {
            FullHttpRequest httpRequest = (FullHttpRequest) o;
            chatMsgNettyService.handHttpRequest(channelHandlerContext, httpRequest);
        } else if (o instanceof WebSocketFrame) {
            WebSocketFrame webSocketFrame = (WebSocketFrame) o;
            chatMsgNettyService.handWebsocketFrame(channelHandlerContext, webSocketFrame);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
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
        cause.printStackTrace();
        ctx.close();
    }

}
