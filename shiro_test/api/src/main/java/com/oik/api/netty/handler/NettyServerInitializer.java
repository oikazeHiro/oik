package com.oik.api.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //因为基于http协议，使用http的编码和解码器
        pipeline.addLast("http-codec", new HttpServerCodec());
        //心跳检测
        pipeline.addLast("ping", new IdleStateHandler(2, 1, 3, TimeUnit.MINUTES));
        // 说明
        // 1. http数据在传输过程中是分段, HttpObjectAggregator ，就是可以将多个段聚合
        // 2. 这就就是为什么，当浏览器发送大量数据时，就会发出多次http请求
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        // 文件传输
        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
        // websocket
        pipeline.addLast("websocket", new WebSocketServerProtocolHandler("ws"));
//        pipeline.addLast(new NettyServerHandler()); // 等前端的protobuf 和实体设计搞好再用这个
        pipeline.addLast(new ChatMsgServerHander());
    }
}
