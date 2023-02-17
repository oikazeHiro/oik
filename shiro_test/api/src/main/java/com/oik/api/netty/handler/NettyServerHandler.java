package com.oik.api.netty.handler;

import com.oik.api.netty.pojo.Message;
import com.oik.api.netty.service.Read0Service;
import com.oik.util.channelUitl.ChannelOperateUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

import static com.oik.util.redis.CacheClient.EXECUTOR_SERVICE;

@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<Message.Msg> {

    @Resource
    private Read0Service read0Service;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message.Msg msg) {
        EXECUTOR_SERVICE.submit(() -> {
            try {
                read0Service.operate(ctx, msg);
            } catch (Exception e) {
                ctx.writeAndFlush(e.getMessage());
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ChannelOperateUtil.addSocketGroup(ctx.channel());
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
