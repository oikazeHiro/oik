package com.oik.api.netty.util;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelOperateUtil {
    private static final ConcurrentHashMap<String, ChannelId> socketChannelHashMap = new ConcurrentHashMap<>();
    private static final List<Channel> socketChannelList = new LinkedList<>();

    private static final ChannelGroup socketGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void addChannelMap(String id, ChannelId ch) {
        socketChannelHashMap.put(id, ch);
    }

    public static ChannelId getChannelMap(String id) {
        return socketChannelHashMap.get(id);
    }

    public static boolean addChannelList(Channel ch) {
        return socketChannelList.add(ch);
    }

    public static boolean removeChannelList(Channel ch) {
        return socketChannelList.remove(ch);
    }

    public static boolean addSocketGroup(Channel ch) {
        return socketGroup.add(ch);
    }

    public static boolean removeSocketGroup(Channel ch) {
        return socketGroup.remove(ch);
    }

    public static Channel findChannel(ChannelId id) {
        return socketGroup.find(id);
    }

    public static void sendAll(Object tws) {
        socketGroup.writeAndFlush(tws);
    }
}
