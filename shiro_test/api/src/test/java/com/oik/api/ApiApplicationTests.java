package com.oik.api;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oik.dao.entity.LoginLog;
import com.oik.dao.entity.Menu;
import com.oik.dao.entity.Role;
import com.oik.dao.entity.User;
import com.oik.service.exception.Result;
import com.oik.service.service.*;
import com.oik.util.dto.UserDTO;
import com.oik.util.redis.CacheClient;
import com.oik.util.redis.UserHolder;
import com.oik.util.uncategorized.EncryptUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

@SpringBootTest
class ApiApplicationTests {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CacheClient cacheClient;

    @Autowired
    private UserService userService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private LoginLogService loginLogService;


    @Test
    void contextLoads() {
        List<Role> admin = roleService.findUserRole("admin");
        admin.stream().forEach(System.out::println);

        Set<String> admin1 = menuService.findUserPermissions("admin");
        admin1.stream().forEach(System.out::println);
    }

    @Test
    void md5Test() {
        String content = "test中文";

        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();//随机生成密钥

        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

        String encryptHex = aes.encryptHex(content);//加密为16进制表示
        String decryptStr = aes.decryptStr(encryptHex);//解密为字符串
        System.out.println("encryptHex = " + encryptHex);
        System.out.println("decryptStr = " + decryptStr);
    }

    @Test
    void encodeTest() {
        String encode = EncryptUtil.encode("123456");
        System.out.println("encode = " + encode);
        System.out.println("EncryptUtil.matches(\"123456\",encode) = " + EncryptUtil.matches("123456", encode));
        System.out.println("EncryptUtil.matches(\"12356\",encode) = " + EncryptUtil.matches("12356", encode));
    }

    @Test
    void roleTest() {
        Set<String> admin1 = menuService.findUserPermissions("mrbird");
        admin1.stream().forEach(System.out::println);
    }

    @Test
    void pageTest() {
        Page page = new Page<>(1, 2);
        User user = new User();
        user.setUsername("ad");
        Result result = userService.getUser(page, user);
        System.out.println("JSONUtil.toJsonStr(result) = " + JSONUtil.toJsonStr(result));
    }

    @Test
    void getMenu(){
        UserHolder.saveUser(new UserDTO("admin"));
        List<Menu> menus = menuService.getMenus();
        System.out.println("menus = " +  JSONUtil.toJsonStr(menus));
    }

    @Test
    void getCount(){
//        System.out.println("count = " + count);
        long min = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long max = LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        LocalDateTime minTime = LocalDateTime.ofEpochSecond(min / 1000, 0, ZoneOffset.ofHours(8));
        LocalDateTime maxTime = LocalDateTime.ofEpochSecond(max / 1000, 0, ZoneOffset.ofHours(8));
        long TodayIp = loginLogService.count(new QueryWrapper<LoginLog>().select("distinct(ip)").lambda().between(LoginLog::getLoginTime,
                minTime,
                maxTime));
        System.out.println("count = " + TodayIp);
    }

    @Test
    void getIndex() {
        Result admin = userService.index("admin");
        System.out.println("JSONUtil.toJsonStr(admin) = " + JSONUtil.toJsonStr(admin));
    }

    @Test
    void menus() {
        Page page = new Page(1, 10);
        Menu menu = new Menu();
//        menu.setMenuName()
        IPage<Menu> menus = menuService.menus(page, menu);
        System.out.println("JSONUtil.toJsonStr(menus) = " + JSONUtil.toJsonStr(menus));
    }

}
