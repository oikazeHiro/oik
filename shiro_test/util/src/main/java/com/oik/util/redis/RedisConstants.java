package com.oik.util.redis;

public class RedisConstants {
    public static final String LOGIN_CODE_KEY = "login:code:";
    public static final Long LOGIN_CODE_TTL = 2L;
    public static final String LOGIN_USER_KEY = "login:token:";
    public static final Long LOGIN_USER_TTL = 60L;

    public static final Long CACHE_NULL_TTL = 2L;

    public static final Long CACHE_TTL = 30L;
    public static final String CACHE_SHOP_KEY = "cache:shop:";

    public static final String LOCK_SHOP_KEY = "lock:shop:";
    public static final Long LOCK_SHOP_TTL = 10L;

    public static final String SECKILL_STOCK_KEY = "seckill:stock:";
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

    // 存储在线用户的 zset前缀
    public static final String ACTIVE_USERS_ZSET_PREFIX = "oik.user.active";

    // 排序规则： descend 降序
    public static final String ORDER_DESC = "descend";
    // 排序规则： ascend 升序
    public static final String ORDER_ASC = "ascend";

    // 按钮
    public static final Integer TYPE_BUTTON = 1;
    // 菜单
    public static final Integer TYPE_MENU = 0;

    // 网络资源 Url
    public static final String MEIZU_WEATHER_URL = "http://aider.meizu.com/app/weather/listWeather";
    public static final String MRYW_TODAY_URL = "https://interface.meiriyiwen.com/article/today";
    public static final String MRYW_DAY_URL = "https://interface.meiriyiwen.com/article/day";
    public static final String TIME_MOVIE_HOT_URL = "https://api-m.mtime.cn/Showtime/LocationMovies.api";
    public static final String TIME_MOVIE_DETAIL_URL = "https://ticket-api-m.mtime.cn/movie/detail.api";
    public static final String TIME_MOVIE_COMING_URL = "https://api-m.mtime.cn/Movie/MovieComingNew.api";
    public static final String TIME_MOVIE_COMMENTS_URL = "https://ticket-api-m.mtime.cn/movie/hotComment.api";
    //数据范围权限
    public static final int DATA_FILTER_ALL = 0;
    public static final int DATA_FILTER_DEPT = 1;
    public static final int DATA_FILTER_OWN = 2;

    public static final int STATUS_VALID = 1;

    public static final int STATUS_LOCK = 0;

    public static final String SYS_DICT = "oik.cache.dict";
    public static final String SYS_GREET = "oik.cache.greet";
    public static final String SYS_GREET_LOCK = "oik.cache.greet.lock.";
}
