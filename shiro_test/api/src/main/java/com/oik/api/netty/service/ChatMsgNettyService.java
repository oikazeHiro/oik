package com.oik.api.netty.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/14 9:34
 */
public interface ChatMsgNettyService {

    /**
     * 处理客户端向服务端发起 http 握手请求的业务
     * WebSocket在建立握手时，数据是通过HTTP传输的。但是建立之后，在真正传输时候是不需要HTTP协议的。
     * WebSocket 连接过程：
     * 首先，客户端发起http请求，经过3次握手后，建立起TCP连接；http请求里存放WebSocket支持的版本号等信息，如：Upgrade、Connection、WebSocket-Version等；
     * 然后，服务器收到客户端的握手请求后，同样采用HTTP协议回馈数据；
     * 最后，客户端收到连接成功的消息后，开始借助于TCP传输信道进行全双工通信。
     */
    void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest httpRequest);

    /**
     * 处理客户端与服务端之间的 websocket 业务
     */
    void handWebsocketFrame(ChannelHandlerContext ctx, WebSocketFrame websocket) throws JsonProcessingException;

    void sendHttpResponse(ChannelHandlerContext ctx, DefaultFullHttpResponse response);

    void clearSession(Channel ch);
}
