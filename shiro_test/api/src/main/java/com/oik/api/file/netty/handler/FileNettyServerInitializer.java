package com.oik.api.file.netty.handler;

import com.oik.api.file.netty.projo.DataInfoProto;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/23 13:55
 */
public class FileNettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("http-codec", new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 512));
        // 文件传输
        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
        pipeline.addLast("ProtobufVarint32FrameDecoder", new ProtobufVarint32FrameDecoder());
        pipeline.addLast("ProtobufDecoder",new ProtobufDecoder(DataInfoProto.DataInfo.getDefaultInstance()));
        pipeline.addLast("ProtobufEncoder",new ProtobufEncoder());
        pipeline.addLast(new FileNettyServerHandler());

    }
}
