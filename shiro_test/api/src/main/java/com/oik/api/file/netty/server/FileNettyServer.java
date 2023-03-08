package com.oik.api.file.netty.server;

import com.oik.api.file.netty.handler.FileNettyServerInitializer;
import com.oik.util.str.YamlReader;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/23 13:39
 */
@Slf4j
public class FileNettyServer {
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private static final FileNettyServer FILE_NETTY_SERVER = new FileNettyServer();
    private final int prot = (int) YamlReader.getValueByKey("oik.netty.filePort");

    private void startFile() {
        try {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new FileNettyServerInitializer());
            ChannelFuture sync = bootstrap.bind(prot).sync();
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            log.error(e.getMessage());
        }finally {

        }

    }

    private void close(){
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
