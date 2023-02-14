package com.oik.api.netty.constant;

/**
 * @author 15093
 * @description TODO
 * @date 2023/2/14 10:47
 */
public enum MessageTypeConstant {
    UPDATE_USER_LIST_SYSTEM_MESSAGE(3, "更新用户列表");
    private int type;
    private String description;

    MessageTypeConstant(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
