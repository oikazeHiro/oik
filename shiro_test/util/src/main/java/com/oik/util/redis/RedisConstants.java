package com.oik.util.redis;

public class RedisConstants {
    public static final String LOGIN_CODE_KEY = "login:code:";
    public static final Long LOGIN_CODE_TTL = 2L;
    public static final String LOGIN_USER_KEY = "login:token:";
    public static final Long LOGIN_USER_TTL = 60L;

    public static final Long CACHE_NULL_TTL = 2L;

    public static final Long CACHE_TTL = 60L;
    public static final String CACHE_SHOP_KEY = "cache:shop:";

    public static final String LOCK_SHOP_KEY = "lock:shop:";
    public static final Long LOCK_SHOP_TTL = 10L;

    public static final String DESKILL_STOCK_KEY = "deskill:stock:";
    public static final String BLOG_LIKED_KEY = "blog:liked:";
    public static final String FEED_KEY = "oik:";
    public static final String SHOP_GEO_KEY = "shop:geo:";
    public static final String USER_SIGN_KEY = "sign:";

    public static final String UNDER_LINE = "_";
    // user缓存前缀
    public static final String USER_CACHE_PREFIX = "oik.cache.user.";
    // user角色缓存前缀
    public static final String USER_ROLE_CACHE_PREFIX = "oik.cache.user.role.";
    public static final String USER_ROLE_LOCK_PREFIX = "oik.cache.user.role.lock.";

    // user权限缓存前缀
    public static final String USER_PERMISSION_CACHE_PREFIX = "oik.cache.user.permission.";
    // user部门数据权限缓存前缀
    public static final String USER_PERMISSION_DEPT_DATA_CACHE_PREFIX = "oik.cache.user.dept.data.permission.";
    // user个性化配置前缀
    public static final String USER_CONFIG_CACHE_PREFIX = "oik.cache.user.config.";
    public static final String USER_CONFIG_CACHE_MENU = "oik.cache.user.menu.";
    // token缓存前缀
    public static final String TOKEN_CACHE_PREFIX = "oik.cache.token.";

    // 存储在线用户的 set前缀
    public static final String ACTIVE_USERS_SET_PREFIX = "oik.user.active";

    // 排序规则： descend 降序
    public static final String ORDER_DESC = "descend";
    // 排序规则： ascend 升序
    public static final String ORDER_ASC = "ascend";

    // 按钮
    public static final Integer TYPE_BUTTON = 1;
    // 菜单
    public static final Integer TYPE_MENU = 0;
    public static final int PRIVATE_CHAT_CODE = 1;
    public static final int GROUP_CHAT_CODE = 2;
    public static final int PING_MESSAGE_CODE = 3;
    public static final int PONG_CHAT_CODE = 4;
    public static final int SYSTEM_MESSAGE_CODE = 5;
    public static final int UPDATE_USER_LIST_SYSTEM_MESSAGE = 3;

    // 网络资源 Url

    //数据范围权限
    public static final int DATA_FILTER_ALL = 0;
    public static final int DATA_FILTER_DEPT = 1;
    public static final int DATA_FILTER_OWN = 2;

    public static final int STATUS_VALID = 1;

    public static final int STATUS_LOCK = 0;

    public static final String SYS_DICT = "oik.cache.dict";
    public static final String SYS_GREET = "oik.cache.greet";
    public static final String SYS_GREET_LOCK = "oik.cache.greet.lock.";
    public static final String SYS_DEPT = "oik.cache.dept";

    public static final String SYS_SELECT_MENU = "oik.cache.select.menu";
}
