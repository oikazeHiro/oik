package com.oik.api.config;


import com.oik.util.exception.MyException;
import com.oik.util.exception.Result;
import com.oik.util.exception.ResultEnum;
import com.oik.util.exception.ResultUtil;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异常建议
 * 异常控制处理器
 *
 * @author LEAF
 * &#064;date  2022-09-07
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    /**
     * 捕捉校验异常(BindException)
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Result validException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, Object> error = this.getValidError(fieldErrors);
        e.printStackTrace();
        return ResultUtil.getError(ResultEnum.ERROR.getCode(), error.get("errorMsg").toString(), error.get("errorList"));
    }

    /**
     * 捕捉404异常
     *
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handle(NoHandlerFoundException e) {
        e.printStackTrace();
        return ResultUtil.getError(ResultEnum.NO_PAGE.getCode(), "未找到api接口请校验url地址" + e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result Unauthorized(UnauthorizedException e) {
        e.printStackTrace();
        return ResultUtil.getError(407, "未经授权" + e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public Result Unauthorized(Throwable e) {
        e.printStackTrace();
        return ResultUtil.getError(500, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result Unauthorized(IllegalArgumentException e) {
        e.printStackTrace();
        return ResultUtil.getError(400, e.getMessage());
    }

    @ExceptionHandler(SignatureException.class)
    public Result method(SignatureException e) {
        e.printStackTrace();
        return ResultUtil.getError(ResultEnum.NO_PAGE.getCode(), e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public Result method(AuthenticationException e) {
        e.printStackTrace();
        return ResultUtil.getError(ResultEnum.NO_PAGE.getCode(), e.getMessage());
    }
    @ExceptionHandler(UnknownHostException.class)
    public Result method(UnknownHostException e) {
        e.printStackTrace();
        return ResultUtil.getError(405, e.getMessage());
    }

    @ExceptionHandler(MyException.class)
    public Result method(MyException e) {
        e.printStackTrace();
        return ResultUtil.getError(e.getCode(), e.getMessage());
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result method(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return ResultUtil.getError(ResultEnum.NO_PAGE.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public Result nullptr(NullPointerException e) {
        e.printStackTrace();
        return ResultUtil.getError(ResultEnum.SYSTEM_EXCEPTION.getCode(), e.getMessage());
    }

    /**
     * 捕捉其他所有异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result globalException(Throwable e) {
        e.printStackTrace();
        return ResultUtil.getError(ResultEnum.SYSTEM_EXCEPTION);
    }

    /**
     * 获取状态码
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * 获取校验错误信息
     */
    private Map<String, Object> getValidError(List<FieldError> fieldErrors) {
        Map<String, Object> map = new HashMap<>(16);
        List<String> errorList = new ArrayList<>();
        StringBuffer errorMsg = new StringBuffer("校验异常(ValidException):");
        for (FieldError error : fieldErrors) {
            errorList.add(error.getField() + "-" + error.getDefaultMessage());
            errorMsg.append(error.getField()).append("-").append(error.getDefaultMessage()).append(".");
        }
        map.put("errorList", errorList);
        map.put("errorMsg", errorMsg);
        return map;
    }
}
