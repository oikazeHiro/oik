package com.oik.api.config.aop;

import cn.hutool.json.JSONUtil;
import com.oik.dao.entity.Log;
import com.oik.service.service.LogService;
import com.oik.util.dto.UserDTO;
import com.oik.util.redis.UserHolder;
import com.oik.util.uncategorized.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Resource
    private LogService logService;

    //    @Pointcut("@annotation(com.oik.api.config.aop.EagleEye)")
    @Pointcut("execution(public * com.oik.api.controller.*.*(..))")
    public void loggerOutPointcut() {
    }

    @Around("loggerOutPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // 接收到请求,取出请求域对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        //打印请求的内容
        String username = "";
        try {
            UserDTO user = UserHolder.getUser();
            username = user.getUsername();
        }catch (Exception e) {
            log.info(e.getMessage());
        }
        String str = JSONUtil.toJsonStr(pjp.getArgs());
        String ipAddr = IPUtil.getIpAddr(request);
        String name = pjp.getSignature().getName();
        long startTime = System.currentTimeMillis();
        log.info("请求Url : {}", request.getRequestURL().toString());
        log.info("请求方式 : {}", request.getMethod());
        log.info("请求ip : {}", ipAddr);
        log.info("请求用户 : {}", username);
        log.info("请求控制器 : {}", pjp.getSignature().getDeclaringTypeName());
        log.info("请求方法 : {}", name);
        log.info("请求参数 : {}", str);
        // 执行方法
        Object result = pjp.proceed();
        log.info("请求结束时间：" + LocalDateTime.now());
        log.info("请求耗时：{}", (System.currentTimeMillis() - startTime) + "ms");
        assert response != null;
        Log logSave = new Log(username,
                request.getRequestURL().toString(),
                name,
                (System.currentTimeMillis() - startTime),
                request.getMethod(),
                name.equals("login") || name.equals("register") || name.equals("resetUser") ? "保密" : str,
                ipAddr,
                LocalDateTime.now(),
                Objects.requireNonNull(IPUtil.getCityInfo(ipAddr)).get("city"),
                response.getStatus(),
                JSONUtil.toJsonStr(result),
                "0");
        logService.save(logSave);
        // 处理完请求，返回内容
        return result;
    }
}
