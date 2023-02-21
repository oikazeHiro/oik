package com.oik.api.config.shiro;

import cn.hutool.json.JSONUtil;
import com.oik.dao.entity.Role;
import com.oik.service.service.CacheService;
import com.oik.service.service.MenuService;
import com.oik.service.service.RoleService;
import com.oik.util.dto.UserDTO;
import com.oik.util.redis.CacheClient;
import com.oik.util.redis.RedisConstants;
import com.oik.util.redis.UserHolder;
import com.oik.util.uncategorized.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private RoleService roleService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CacheClient cacheClient;

    /**
     * 大坑，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权模块，获取角色和权限
     *
     * @return simpleAuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = JwtUtil.getUsername(principalCollection.toString());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roleList = CacheClient.selectCacheByTemplate(
                () -> this.cacheService.getRoles(username),
                () -> this.roleService.findUserRole(username));
        Set<String> roleSet = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roleSet);
        Set<String> permissionList = CacheClient.selectCacheByTemplate(
                () -> this.cacheService.getPermissions(username),
                () -> this.menuService.findUserPermissions(username));
        simpleAuthorizationInfo.setStringPermissions(permissionList);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取token
        // HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String token = (String) authenticationToken.getCredentials();
        // String ip = IPUtil.getIpAddr(request);
        String username;
        username = JwtUtil.getUsername(token);
        // 通过用户名查询用户信息
        String value = cacheClient.getValue(RedisConstants.USER_CACHE_PREFIX, username);
        if (StringUtils.isEmpty(value))
            throw new AuthenticationException("请从新登录");
        UserDTO user = JSONUtil.toBean(value, UserDTO.class);
        if (!token.equals(user.getToken()))//||!ip.equals(user.getIp()))
            throw new AuthenticationException("该账号已在其他位置登录");
        UserHolder.saveUser(user);
        return new SimpleAuthenticationInfo(token, token, "oik_shiro_realm");
    }
}
