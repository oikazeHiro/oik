package com.oik.api.netty;

import com.oik.api.netty.handler.NettyServerInitializer;
import com.oik.util.str.YamlReader;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 */
@Slf4j
@Component
public class NettyServer {
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private static final NettyServer NETTY_SERVER = new NettyServer();

    private NettyServer() {
    }

    // 设置端口
    //    @Value("${oik.netty.port}")
    private final int nettyPort = (int) YamlReader.getValueByKey("oik.netty.port");

    //设置单例模型
    public static NettyServer getInstance() {
        return NETTY_SERVER;
    }

    public void start() {
        try {
            //创建两个线程组
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();
            //服务对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //构建工程线程池
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // 如果大于队列的最大长度，请求会被拒绝
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new NettyServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(nettyPort).sync();
            log.info("---netty socket server start port: " + nettyPort + "---");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("netty start error: " + e.getMessage());
        } finally {
            close();
        }
    }

    public void close() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }


}
