package com.oik.util.exception;

/**
 * 自定义枚举异常
 *
 * @author oik
 * @date 2022/7/23 15:35
 */
public enum ResultEnum {
    /**
     * 成功.: 200 (因为http中的状态码200一般都是表示成功)
     */
    SUCCESS(200, "成功"),

    /**
     * 系统异常. ErrorCode : -1
     */
    SYSTEM_EXCEPTION(500, "系统异常"),

    /**
     * 未知异常. ErrorCode : 01
     */
    UNKNOWN_EXCEPTION(1, "未知异常"),

    /**
     * 服务异常. ErrorCode : 02
     */
    SERVICE_EXCEPTION(2, "服务异常"),

    /**
     * 业务错误. ErrorCode : 03
     */
    MY_EXCEPTION(3, "业务错误"),
    /**
     * 提示级错误. ErrorCode : 04
     */
    INFO_EXCEPTION(4, "提示级错误"),

    /**
     * 数据库操作异常. ErrorCode : 05
     */
    DB_EXCEPTION(5, "数据库操作异常"),

    /**
     * 参数验证错误. ErrorCode : 06
     */
    PARAM_EXCEPTION(6, "参数验证错误"),
    /**
     * 参数验证错误. ErrorCode : 5001数据为空
     */
    DATA_NULL(5001, "数据为空"),
    /**
     * 未认证. ErrorCode : 4001
     */
    UNAUTHENTICATED(401, "未认证"),
    /**
     * 未授权. ErrorCode : 4002
     */
    UNAUTHORIZED(4002, "未授权"),
    /**
     * 用户名不存在. ErrorCode : 4003
     */
    USERNAME_NOT_EXIST(4003, "用户名不存在"),
    /**
     * 密码错误. ErrorCode : 4004
     */
    PASSWORD_ERROR(4004, "用户名或密码错误"),
    /**
     * 无权限. ErrorCode : 4005
     */
    UNAUTHORIZED_ERROR(4005, "开发者账号，无法删除"),
    ERROR(10400, "请求失败"),
    /**
     * 无页面 . ErrorCode : 4004
     */
    NO_PAGE(401, "请求失败"),
    STATUS_LOCK(401, "账号已被禁用"),

    CLEAR_TOKEN(411, "清除令牌"),
    USERNAME_IS_USE(400, "用户名已被使用"),
    NOT_FOUND_CHANNEL_ID(500, "未找到管道Id"),
    NOT_FOUND_CHANNEL(500, "未找到管道"),
    DICT_CODE_OR_KEY(500, "code或key重复");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
