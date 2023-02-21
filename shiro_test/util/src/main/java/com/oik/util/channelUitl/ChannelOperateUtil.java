package com.oik.util.channelUitl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelOperateUtil {

    /**
     * @Author 15093
     * @Description // 用 ConcurrentHashMap 来存储用户和管道的绑定关系 第一个参数为 userId 或者其他唯一的标识，第二个参数是 ChannelId
     * @Date 9:06
     * @Param
     * @return
     **/
    private static final ConcurrentHashMap<String, ChannelId> socketChannelHashMap = new ConcurrentHashMap<>();
    /**
     * @Author 15093
     * @Description // 存储所有管道 方便公告什么的 不过好像没啥用了
     * @Date 9:10
     * @Param
     * @return
     **/
    private static final List<Channel> socketChannelList = new LinkedList<>();

    /**
     * @Author 15093
     * @Description // Channel的集合
     * @Date 9:12
     * @Param
     * @return
     **/
    private static final ChannelGroup socketGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * @return void
     * @Author 15093
     * @Description // 向 socketChannelHashMap 里添加数据
     * @Date 9:13
     * @Param [id, ch]
     **/
    public static void addChannelMap(String id, ChannelId ch) {
        socketChannelHashMap.put(id, ch);
    }

    /**
     * @return io.netty.channel.ChannelId
     * @Author 15093
     * @Description // 获取  socketChannelHashMap 中对应的的ChannelId
     * @Date 9:14
     * @Param [id]
     **/
    public static ChannelId getChannelMap(String id) {
        return socketChannelHashMap.get(id);
    }

    /**
     * @return boolean
     * @Author 15093
     * @Description // 向 socketChannelList 添加管道
     * @Date 9:14
     * @Param [ch]
     **/
    public static boolean addChannelList(Channel ch) {
        return socketChannelList.add(ch);
    }

    /**
     * @return boolean
     * @Author 15093
     * @Description // 删除 socketChannelList 中的管道
     * @Date 9:15
     * @Param [ch]
     **/
    public static boolean removeChannelList(Channel ch) {
        return socketChannelList.remove(ch);
    }

    /**
     * @return boolean
     * @Author 15093
     * @Description // 向 socketGroup 添加管道
     * @Date 9:16
     * @Param [ch]
     **/
    public static boolean addSocketGroup(Channel ch) {
        return socketGroup.add(ch);
    }

    /**
     * @return boolean
     * @Author 15093
     * @Description // socketGroup 删除对应管道
     * @Date 9:16
     * @Param [ch]
     **/
    public static boolean removeSocketGroup(Channel ch) {
        return socketGroup.remove(ch);
    }

    /**
     * @return io.netty.channel.Channel
     * @Author 15093
     * @Description // socketGroup 寻找对应管道
     * @Date 9:17
     * @Param [id]
     **/
    public static Channel findChannel(ChannelId id) {
        return socketGroup.find(id);
    }

    /**
     * @return void
     * @Author 15093
     * @Description // 发送公告
     * @Date 9:18
     * @Param [tws]
     **/
    public static ChannelGroupFuture sendAll(Object tws) {
        return socketGroup.writeAndFlush(tws);
    }

    public static Set<String> getMapKeySet() {
        return socketChannelHashMap.keySet();
    }

    public static boolean removeSocketGroupByValue(ChannelId id) {
        Collection<ChannelId> values = socketChannelHashMap.values();
        return values.remove(id);
    }


}
