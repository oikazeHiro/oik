package com.oik.service.exception;

/**
 * 自定义全局返回工具类
 *
 * @author oik
 * &#064;date  2022/7/23 15:35
 */
public class ResultUtil {

    /**
     * 操作成功的处理流程
     */
    public static <T> Result<T> getSuccess(T data) {
        Result<T> result = new Result<>();
        //设置操作成功的返回码
        result.setCode(200);
        //设置操作成功的消息
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> getSuccess(T data, Long count) {
        Result<T> result = new Result<>();
        //设置操作成功的返回码
        result.setCode(200);
        //设置操作成功的消息
        result.setMsg("success");
        result.setData(data);
        result.setCount(count);
        return result;
    }

    public static <T> Result<T> getSuccess(T data, Long count, String message) {
        Result<T> result = new Result<>();
        //设置操作成功的返回码
        result.setCode(200);
        //设置操作成功的消息
        result.setMsg(message);
        result.setData(data);
        result.setCount(count);
        return result;
    }

    public static <T> Result<T> getSuccess(T data, String message) {
        Result<T> result = new Result<>();
        //设置操作成功的返回码
        result.setCode(200);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    /**
     * 重载返回成功的方法，因为有时候我们不需要任何的消息数据被返回
     */
    public static Result getSuccess() {
        Result result = new Result<>();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    /**
     * 操作失败的处理流程
     *
     * @param code 错误码
     * @param msg  错误消息
     * @param o    错误数据（其实这个一般都不需要的，因为都已经返回失败了，数据都没必要返回）
     */
    public static <T> Result<T> getError(Integer code, String msg, T o) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(o);
        return result;
    }

    /**
     * 重载，操作失败的方法（因为操作失败一般都不需要返回数据内容）
     */
    public static <T> Result getError(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
