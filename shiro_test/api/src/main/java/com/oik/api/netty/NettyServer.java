package com.oik.api.netty;

import com.oik.api.netty.handler.NettyServerInitializer;
import com.oik.util.str.YamlReader;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
@Slf4j
@Component
public class NettyServer {
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ChannelFuture channelFuture;
    private ServerBootstrap serverBootstrap;
    private static ConcurrentHashMap<String, Channel> socketChannelHashMap = new ConcurrentHashMap<>();
    private static final NettyServer NETTY_SERVER = new NettyServer();
    private NettyServer() {
    }

    public static NettyServer getInstance(){
        return NETTY_SERVER;
    }

//    @Value("${oik.netty.port}")
    private Integer nettyPort = (Integer) YamlReader.getValueByKey("oik.netty.port");
    public void start(){
        try {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();
            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new NettyServerInitializer());
            channelFuture = serverBootstrap.bind(nettyPort).sync();
            log.info("---netty socket server start port: "+nettyPort+"---");
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e) {
            log.error("netty start error: " + e.getMessage());
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
