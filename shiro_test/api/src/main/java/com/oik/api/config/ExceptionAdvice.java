package com.oik.api.config;


import com.auth0.jwt.exceptions.*;
import com.oik.service.exception.MyException;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultEnum;
import com.oik.service.exception.ResultUtil;
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
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
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
        return ResultUtil.getError(ResultEnum.ERROR.getCode(), error.get("errorMsg").toString(), error.get("errorList"));
    }

    /**
     * 捕捉404异常
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handle(NoHandlerFoundException e) {
        return ResultUtil.getError(ResultEnum.NO_PAGE.getCode(), "未找到api接口请校验url地址");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result Unauthorized(UnauthorizedException e) {
        return ResultUtil.getError(407, e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public Result Unauthorized(Throwable e) {
        return ResultUtil.getError(500, e.getMessage());
    }

    @ExceptionHandler(SignatureException.class)
    public Result method(SignatureException e) {
        return ResultUtil.getError(ResultEnum.NO_PAGE.getCode(), e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public Result method(AuthenticationException e) {
        return ResultUtil.getError(ResultEnum.NO_PAGE.getCode(), e.getMessage());
    }
    @ExceptionHandler(UnknownHostException.class)
    public Result method(UnknownHostException e) {
        return ResultUtil.getError(405, e.getMessage());
    }

    @ExceptionHandler(MyException.class)
    public Result method(MyException e) {
        return ResultUtil.getError(e.getCode(), e.getMessage());
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result method(HttpRequestMethodNotSupportedException e) {
        return ResultUtil.getError(ResultEnum.NO_PAGE.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public Result nullpo(NullPointerException e) {
        return ResultUtil.getError(ResultEnum.SystemException.getCode(), e.getMessage());
    }

    /**
     * 捕捉其他所有异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result globalException(HttpServletRequest request, Throwable e) {
        //如果是自定义的异常
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return ResultUtil.getError(myException.getCode(), myException.getMessage());
        } else if (e instanceof UnsupportedEncodingException) {
            return ResultUtil.getError(ResultEnum.ParamException.getCode(), "JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
        } else if (e instanceof JWTDecodeException) {
            return ResultUtil.getError(ResultEnum.ParamException.getCode(), "解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        } else if (e instanceof TokenExpiredException) {
            return ResultUtil.getError(ResultEnum.ParamException.getCode(), "TOKEN已过期请重新登陆");
        } else if (e instanceof SignatureVerificationException) {
            return ResultUtil.getError(ResultEnum.ParamException.getCode(), "TOKEN签名不一致");
        } else if (e instanceof AlgorithmMismatchException) {
            return ResultUtil.getError(ResultEnum.ParamException.getCode(), "TOKEN算法不匹配");
        } else if (e instanceof InvalidClaimException) {
            return ResultUtil.getError(ResultEnum.ParamException.getCode(), "TOKEN失效的payload异常");
        } else {
            log.error("发生异常时间:{}", LocalDateTime.now());
            log.error("异常描述:{}", e);
            return ResultUtil.getError(ResultEnum.SystemException.getCode(), ResultEnum.SystemException.getMsg());
        }
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
