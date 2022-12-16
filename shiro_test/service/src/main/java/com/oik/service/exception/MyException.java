package com.oik.service.exception;

/**
 * 自定义异常处理
 *
 * @author oik
 * @date 2022/7/23 15:35
 */
public class MyException extends RuntimeException {
    private Integer code;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public MyException(String message) {
        super(message);
    }

    /**
     * 构造器重载，主要是自己考虑某些异常自定义一些返回码
     *
     * @param code    返回碼
     * @param message 消息
     */
    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 抛出自定义枚举类异常
     */
    public MyException(ResultEnum myException) {
        super(myException.getMsg());
        this.code = myException.getCode();
    }

    /**
     * 抛出自定义重写枚举类异常
     *
     * @param code 重写code码
     * @param msg  重写msg消息
     */
    public MyException(ResultEnum myException, int code, String msg) {
        super(msg);
        this.code = code;
    }

    /**
     * 抛出自定义重写枚举类异常
     *
     * @param msg 重写msg消息
     */
    public MyException(ResultEnum myException, String msg) {
        super(msg);
        this.code = myException.getCode();
    }
}
