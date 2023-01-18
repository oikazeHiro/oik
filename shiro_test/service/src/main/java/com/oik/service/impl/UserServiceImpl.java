package com.oik.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.oik.dao.entity.LoginLog;
import com.oik.dao.entity.User;
import com.oik.dao.mapper.UserMapper;
import com.oik.service.exception.MyException;
import com.oik.service.exception.Result;
import com.oik.service.exception.ResultEnum;
import com.oik.service.exception.ResultUtil;
import com.oik.service.service.LoginLogService;
import com.oik.service.service.UserService;
import com.oik.util.dto.UserDTO;
import com.oik.util.redis.CacheClient;
import com.oik.util.redis.RedisConstants;
import com.oik.util.redis.UserHolder;
import com.oik.util.uncategorized.EncryptUtil;
import com.oik.util.uncategorized.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@Slf4j
@Service
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private CacheClient cacheClient;
    @Resource
    private LoginLogService loginLogService;
    @Override
    public String findSubordinates(Long deptId) {
        return baseMapper.findSubordinates(deptId);
    }

    @Override
    public User findByName(String username) {
        return getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public Result<UserDTO> login(User user) throws MyException, UnsupportedEncodingException {
        User byName = this.findByName(user.getUsername());
        if (byName == null) {
            throw new MyException(ResultEnum.PASSWORD_ERROR);
        }
        if (!EncryptUtil.matches(user.getPassword(), byName.getPassword())) {
            throw new MyException(ResultEnum.PASSWORD_ERROR);
        }
        if (RedisConstants.STATUS_LOCK == byName.getStatus()) {
            throw new MyException(ResultEnum.STATUS_LOCK);
        }
        UserDTO userDTO = updateLoginTime(byName.getUsername());
        //保存登录记录
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(user.getUsername());
        byName.setLastLoginTime(LocalDateTime.now());
        updateById(byName);
            loginLogService.saveLoginLog(loginLog);
        String token = JwtUtil.createToken(byName.getUsername(),System.currentTimeMillis());
        userDTO.setToken(token);
        cacheClient.set(RedisConstants.USER_CACHE_PREFIX+userDTO.getUsername(),userDTO);
        log.info(byName.getUsername()+"登录");
        return ResultUtil.getSuccess(userDTO, "Login success");
    }

    @Override
    public UserDTO updateLoginTime(String username) throws MyException {
        User user = new User();
        user.setLastLoginTime(LocalDateTime.now());
        update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        User byName = findByName(username);
        return BeanUtil.copyProperties(byName, UserDTO.class);
    }

    @Override
    public Result register(User user) {
        User one = getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        if (one != null) throw new MyException(ResultEnum.USERNAME_IS_USE);
        user.setPassword(EncryptUtil.encode(user.getPassword()));
        save(user);
        return ResultUtil.getSuccess();
    }

    @Override
    public Result<Boolean> logout() {
        try {
            UserDTO userDTO = UserHolder.getUser();
            String username = userDTO.getUsername();
            cacheClient.delete(RedisConstants.USER_CACHE_PREFIX + username);
            cacheClient.delete(RedisConstants.USER_ROLE_CACHE_PREFIX + username);
            cacheClient.delete(RedisConstants.USER_PERMISSION_CACHE_PREFIX + username);
            cacheClient.delete(RedisConstants.USER_CONFIG_CACHE_MENU + username);
        } catch (Exception e) {
            throw new MyException(ResultEnum.ServiceException);
        }
        return ResultUtil.getSuccess(true);
    }

    @Override
    public Result<Page<User>> getUser(Page page, User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(user.getUsername() != null, User::getUsername, user.getUsername())
                .eq(user.getStatus() != null, User::getStatus, user.getStatus())
                .orderByDesc(User::getLastLoginTime);
        page = this.baseMapper.selectPage(page, queryWrapper);
        return ResultUtil.getSuccess(page);
    }

    @Override
    public Result<Map<String, Object>> index(String username) {
        //获取访问记录
        long totalVisitCount = loginLogService.count();
        long min = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long max = LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        LocalDateTime minTime = LocalDateTime.ofEpochSecond(min / 1000, 0, ZoneOffset.ofHours(8));
        LocalDateTime maxTime = LocalDateTime.ofEpochSecond(max / 1000, 0, ZoneOffset.ofHours(8));
        long todayVisitCount = loginLogService.count(new LambdaQueryWrapper<LoginLog>()
                .between(LoginLog::getLoginTime,
                        minTime,
                        maxTime));
        long todayIp = loginLogService.count(new QueryWrapper<LoginLog>().select("distinct(ip)").lambda().between(LoginLog::getLoginTime,
                minTime,
                maxTime));
        List<Map<String, Object>> lastSevenVisitCount = loginLogService.findLastSevenDaysVisitCount(null);
        List<Map<String, Object>> lastSevenUserVisitCount = loginLogService.findLastSevenDaysVisitCount(username);
        Map<String, Object> data = new HashMap<>();
        data.put("totalVisitCount", totalVisitCount);
        data.put("todayVisitCount", todayVisitCount);
        data.put("todayIp", todayIp);
        data.put("lastSevenVisitCount", lastSevenVisitCount);
        data.put("lastSevenUserVisitCount", lastSevenUserVisitCount);
        return ResultUtil.getSuccess(data);
    }

    @Override
    public Boolean updateUser(User user) {
        User one = getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        if (one != null && !one.getUserId().equals(user.getUserId())) {
            throw new MyException(ResultEnum.USERNAME_IS_USE);
        }
        return updateById(user);
    }
}
