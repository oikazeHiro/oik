package com.oik.api.config.shiro;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oik.util.application.SpringContextUtil;
import com.oik.util.exception.MyException;
import com.oik.util.exception.Result;
import com.oik.util.exception.ResultEnum;
import com.oik.util.exception.ResultUtil;
import com.oik.util.str.YamlReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private static final List<String> list;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    static {
        list = (List<String>) YamlReader.getValueByKey("oik.shiro.anonUrl");
    }

    @Resource
    private ShiroProperties shiroProperties;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        boolean match = false;
        assert list != null;
        for (String u : list) {
            if (pathMatcher.match(u, httpServletRequest.getRequestURI())){
                match = true;
            }
        }
        if (match) return true;
        if (isLoginAttempt(request, response)) {
            return executeLogin(request, response);
        }
        return false;
    }

    /**
     * 检测Header里面是否包含Authorization字段，有就进行Token登录认证授权
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        String token = this.getAuthzHeader(request);
        return token != null;
    }

    /**
     * 进行AccessToken登录认证授权
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        String jwt = this.getAuthzHeader(request);
        JwtToken token = new JwtToken(jwt);
        try {
            getSubject(request, response).login(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个 option请求，这里我们给 option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

//    @Override
//    public boolean onAccessDenied(ServletRequest request, ServletResponse response) {
//        this.sendChallenge(request, response);
//        return false;
//    }

    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        log.debug("Authentication required: sending 401 Authentication challenge response.");
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpResponse.setCharacterEncoding("utf-8");
        httpResponse.setContentType("application/json; charset=utf-8");
//        final String message = "未认证，请在前端系统进行认证";
        try (PrintWriter out = httpResponse.getWriter()) {
            Result result = ResultUtil.getError(401, "请重新登录");
            String json = SpringContextUtil.getBean("jacksonObjectMapper", ObjectMapper.class).writeValueAsString(result);
            System.out.println(json);
            out.print(json);
        } catch (IOException e) {
            log.error("sendChallenge error：", e);
        }
        return false;
    }

    private void response401(ServletRequest req, ServletResponse resp, String msg) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = httpServletResponse.getWriter()) {
            out.append(JSON.toJSONString(ResultUtil.getError(ResultEnum.UNAUTHENTICATED.getCode(), msg)));
        } catch (IOException e) {
            throw new MyException(ResultEnum.SYSTEM_EXCEPTION, "直接返回Response信息出现IOException异常:" + e.getMessage());
        }
    }
}
