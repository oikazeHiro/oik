package com.oik.util.channelUitl;

import com.oik.util.exception.MyException;
import com.oik.util.exception.ResultEnum;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/17 9:06
 */
@Component
@Slf4j
public class MessageUtil {
    /**
     * 单独发一条消息
     * @param mapKey
     * @param data
     * @return
     */
    public boolean sendMessage(String mapKey,String data) {
        ChannelId id = ChannelOperateUtil.getChannelMap(mapKey);
        if (id == null){
            throw new MyException(ResultEnum.NOT_FOUND_CHANNEL_ID);
        }
        Channel channel = ChannelOperateUtil.findChannel(id);
        if (channel == null) {
            throw new MyException(ResultEnum.NOT_FOUND_CHANNEL);
        }
        ChannelFuture channelFuture = channel.writeAndFlush(new TextWebSocketFrame(data));
        return channelFuture.isSuccess();
    }

    /**
     * 向全部已经连接的通道发消息
     * @param data
     */
    public void sendNotice(String data){
        ChannelGroupFuture channelFutures = ChannelOperateUtil.sendAll(new TextWebSocketFrame(data));
    }

    /**
     * 向指定集合发送消息
     * @param mapKeys
     * @param data
     */
    public void sendGroup(List<String> mapKeys, String data) {
        mapKeys.forEach((e) -> {
            try {
                sendMessage(e, data);
            } catch (Exception a) {
                log.error(a.getMessage());
            }
        });
    }

    public List<String> getMapKeySetList() {
        return new ArrayList<>(ChannelOperateUtil.getMapKeySet());
    }

}
