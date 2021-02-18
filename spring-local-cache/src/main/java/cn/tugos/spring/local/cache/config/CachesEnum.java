package cn.tugos.spring.local.cache.config;

import lombok.Getter;

/**
 * @author qinming
 * @date 2021-02-18 17:10:37
 * <p> 缓存配置枚举 配置缓存的有效时间和最大数量 </p>
 */
@Getter
public enum CachesEnum {

    /**
     * 缓存信息 有效时间和对应的数量
     **/
    COMMON_CACHE(CacheName.COMMON_CACHE, 5, 100), USER_CACHE(CacheName.USER_CACHE, 20, 200);


    /**
     * 缓存的名称
     */
    private final String cacheName;

    /**
     * 缓存的有效期，秒
     */
    private final int ttl;

    /**
     * 缓存的最大数量
     */
    private final int maxSize;

    CachesEnum(String cacheName, int ttl, int maxSize) {
        this.cacheName = cacheName;
        this.ttl = ttl;
        this.maxSize = maxSize;
    }

}
