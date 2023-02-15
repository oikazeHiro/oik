package com.oik.api.netty.service.impl;

import com.alibaba.fastjson2.JSON;
import com.oik.api.netty.constant.MessageCodeConstant;
import com.oik.api.netty.constant.MessageTypeConstant;
import com.oik.api.netty.constant.WebSocketConstant;
import com.oik.api.netty.service.ChatMsgNettyService;
import com.oik.api.netty.util.ChannelOperateUtil;
import com.oik.api.netty.util.RequestParamUtil;
import com.oik.dao.entity.ChatMsg;
import com.oik.dao.entity.User;
import com.oik.service.service.ChatMsgService;
import com.oik.service.service.UserService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.oik.util.redis.RedisConstants.*;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/14 9:34
 */
@Service
@Slf4j
public class ChatMsgNettyServiceImpl implements ChatMsgNettyService {

    // websocket 牵手工厂 不是 握手工程
    private WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(WebSocketConstant.WEB_SOCKET_URL, null, false);
    private WebSocketServerHandshaker handshake; // 网络套接字服务器握手器

    @Resource
    private ChatMsgService chatMsgService;

    @Resource
    private UserService userService;

    @Override
    public void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        // 如果请求失败或者该请求不是客户端向服务端发起的 http 请求，则响应错误信息
        if (!request.decoderResult().isSuccess()
                || !("websocket".equals(request.headers().get("Upgrade")))) {
            // code ：400
            sendHttpResponse(ctx, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        // 新建一个握手
        handshake = factory.newHandshaker(request);
        if (handshake == null) {
            //如果为空，返回响应：不受支持的 websocket 版本
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            Map<String, String> params = RequestParamUtil.urlSplit(request.uri());
            String userId = params.get("userId");
            Channel channel = ctx.channel();
            ChannelId id = channel.id();
            verify(userId, channel);
            ChannelOperateUtil.addSocketGroup(channel);
            ChannelOperateUtil.addChannelMap(userId, id);
            ChannelOperateUtil.addChannelList(channel);
            handshake.handshake(ctx.channel(), request);
            Set<String> userList = ChannelOperateUtil.getMapKeySet();
            Map<String, Object> ext = new HashMap<>();
            ext.put("userList", userList);
            User user = userService.getById(userId);
            ChatMsg chatMsg = new ChatMsg()
                    .setExpandMsg(JSON.toJSONString(ext))
                    .setMsg("user: " + user.getUsername() + " Online,Welcome.")
                    .setCode(MessageCodeConstant.SYSTEM_MESSAGE_CODE.getCode())
                    .setMsgType(MessageTypeConstant.UPDATE_USER_LIST_SYSTEM_MESSAGE.getType());
            ChannelOperateUtil.sendAll(new TextWebSocketFrame(JSON.toJSONString(chatMsg)));
        }
    }

    @Override
    public void handWebsocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        //判断是否是关闭 websocket 的指令
        if (frame instanceof CloseWebSocketFrame) {
            //关闭握手
            handshake.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            clearSession(ctx.channel());
            return;
        }
        //判断是否是ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 判断是否Pong消息
        if (frame instanceof PongWebSocketFrame) {
            ctx.writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //判断是否是二进制消息，如果是二进制消息，抛出异常
        if (!(frame instanceof TextWebSocketFrame)) {
            System.out.println("目前不支持二进制消息");
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            throw new RuntimeException("【" + this.getClass().getName() + "】不支持消息");
        }
        String message = ((TextWebSocketFrame) frame).text();
        log.info(message + "=================");
        try {
            ChatMsg chatMsg = JSON.parseObject(message, ChatMsg.class);
            Integer code = chatMsg.getCode();
            switch (code) {
                // 私聊
                case PRIVATE_CHAT_CODE: {
                    privateChat(ctx.channel(), chatMsg);
                }
                break;
                // 群聊
                case GROUP_CHAT_CODE: {
                    // todo
                }
                break;
                // ping消息
                case PING_MESSAGE_CODE: {
                    // todo
                }
                break;
                // pong消息
                case PONG_CHAT_CODE: {
                    // todo
                }
                break;
                // 系统消息
                case SYSTEM_MESSAGE_CODE: {
                    // todo
                }
                break;

            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void sendHttpResponse(ChannelHandlerContext ctx, DefaultFullHttpResponse response) {
        if (response.status().code() != 200) {
            //创建源缓冲区
            ByteBuf byteBuf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
            //将源缓冲区的数据传送到此缓冲区
            response.content().writeBytes(byteBuf);
            //释放源缓冲区
            byteBuf.release();
        }
        //写入请求，服务端向客户端发送数据
        ChannelFuture channelFuture = ctx.channel().writeAndFlush(response);
        if (response.status().code() != 200) {
            /**
             * 如果请求失败，关闭 ChannelFuture
             * ChannelFutureListener.CLOSE 源码：future.channel().close();
             */
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Transactional
    @Override
    public void clearSession(Channel ch) {
        ChannelId id = ch.id();
        log.info("清除SocketGroup");
        ChannelOperateUtil.removeSocketGroup(ch);
        log.info("清除ChannelList");
        ChannelOperateUtil.removeChannelList(ch);
        log.info("清除SocketGroupByValue");
        ChannelOperateUtil.removeSocketGroupByValue(id);
        ChannelOperateUtil.infoString();
    }

    @Transactional
    public void privateChat(Channel ch, ChatMsg chatMsg) {
        try {
            chatMsgService.sendChatMsg(chatMsg);
            Channel channel = ChannelOperateUtil.findChannel(ChannelOperateUtil.getChannelMap(chatMsg.getAcceptId()));
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(chatMsg)));
            ch.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(chatMsg)));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 验证是否一个key有没有绑定的channel 有就把原来的删掉
     *
     * @param userId
     */
    public void verify(String userId, Channel channel) {
        ChannelId id = ChannelOperateUtil.getChannelMap(userId);
        if (StringUtils.isEmpty(userId)) {
            log.warn("userId为空，关闭管道");
            channel.close();
        }
        if (id != null) {
            log.warn("清除旧的channel");
            Channel ch = ChannelOperateUtil.findChannel(id);
            clearSession(ch);
        }

    }
}
