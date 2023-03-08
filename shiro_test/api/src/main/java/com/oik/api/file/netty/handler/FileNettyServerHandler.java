package com.oik.api.file.netty.handler;

import com.oik.api.file.netty.projo.DataInfoProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/23 15:10
 */
public class FileNettyServerHandler extends SimpleChannelInboundHandler<DataInfoProto.DataInfo> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,DataInfoProto.DataInfo data) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
