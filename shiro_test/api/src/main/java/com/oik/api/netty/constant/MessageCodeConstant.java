package com.oik.api.netty.constant;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/14 10:41
 */
public enum MessageCodeConstant {
    PRIVATE_CHAT_CODE(1, "私聊"),
    GROUP_CHAT_CODE(2, "群聊"),
    PING_MESSAGE_CODE(3, "ping消息"),
    PONG_CHAT_CODE(4, "pong消息"),
    SYSTEM_MESSAGE_CODE(5, "系统消息"),

    ;

    private int code;
    private String description;

    private MessageCodeConstant(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
