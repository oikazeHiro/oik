package com.oik.api.config.shiro;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.oik.service.exception.MyException;
import com.oik.service.exception.ResultEnum;
import com.oik.service.exception.ResultUtil;
import com.oik.util.application.ApplicationContextUtil;
import com.oik.util.dto.UserDTO;
import com.oik.util.redis.CacheClient;
import com.oik.util.redis.RedisConstants;
import com.oik.util.str.YamlReader;
import com.oik.util.uncategorized.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
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
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private static final List<String> list;

    //    private static final String TOKEN = "Authentication"; Authorization
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
            if (pathMatcher.match(u, httpServletRequest.getRequestURI()))
                match = true;
        }
        if (match) return true;
        if (isLoginAttempt(request, response)) {
            try {
                return executeLogin(request, response);
            } catch (Exception e) {
                System.out.println("e.getCause() = " + e.getCause());
                String msg = e.getMessage();
                this.response401(request, response, msg);
                return false;
            }
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
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        String jwt = this.getAuthzHeader(request);
        JwtToken token = new JwtToken(jwt);
        if (!JwtUtil.verify(jwt)) {
            return refreshToken(request,response);
        }
        try {
            getSubject(request, response).login(token);
            return true;
        } catch (AuthenticationException e) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(ResultUtil.getError(401, e.getMessage())));
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

    @Override
    public boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        this.sendChallenge(request, response);
        return false;
    }


    private Boolean refreshToken(ServletRequest request, ServletResponse response){
        String token = this.getAuthzHeader(request);
        CacheClient cacheClient = (CacheClient) ApplicationContextUtil.getBean(CacheClient.class);
        String username = JwtUtil.getUsername(token);
        String value = cacheClient.getValue(RedisConstants.USER_CACHE_PREFIX, username);
        if (StrUtil.isBlank(value)) {
            response401(request, response,"登录过期");
            return false;
        }
        UserDTO userDTO = JSONUtil.toBean(value, UserDTO.class);
        if (!userDTO.getToken().equals(token)) {
            response401(request, response,"账号已在别处登录");
            return false;
        }
        // 刷新AccessToken，设置时间戳为当前最新时间戳
        String jwt;
        try {
            jwt = JwtUtil.createToken(username, System.currentTimeMillis());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        userDTO.setToken(jwt);
        cacheClient.set(RedisConstants.USER_CACHE_PREFIX + userDTO.getUsername(), userDTO);
        // 将新刷新的AccessToken再次进行Shiro的登录
        JwtToken jwtToken = new JwtToken(jwt);
        // 提交给UserRealm进行认证，如果错误他会抛出异常并被捕获，如果没有抛出异常则代表登入成功，返回true
        // 最后将刷新的AccessToken存放在Response的Header中的Authorization字段返回
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Authorization", jwt);
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
        httpServletResponse.setStatus(455);
        getSubject(request, httpServletResponse).login(jwtToken);
        return true;
    }
    private void response401(ServletRequest req, ServletResponse resp, String msg) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = httpServletResponse.getWriter()) {
            out.append(JSON.toJSONString(ResultUtil.getError(ResultEnum.UNAUTHENTICATED.getCode(), msg)));
        } catch (IOException e) {
            throw new MyException(ResultEnum.SystemException, "直接返回Response信息出现IOException异常:" + e.getMessage());
        }
    }
}
