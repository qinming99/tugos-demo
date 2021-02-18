package cn.tugos.spring.local.cache.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 * @author qinming
 * @date 2021-02-18 17:10:37
 *
 * 自定义缓存配置,根据缓存枚举生成缓存配置
 *
 * Caffeine配置说明：
 * initialCapacity=[integer]: 初始的缓存空间大小
 * maximumSize=[long]: 缓存的最大条数
 * maximumWeight=[long]: 缓存的最大权重
 * expireAfterAccess=[duration]: 最后一次写入或访问后经过固定时间过期
 * expireAfterWrite=[duration]: 最后一次写入后经过固定时间过期
 * refreshAfterWrite=[duration]: 创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存
 * recordStats：开发统计功能
 * 注意：
 * expireAfterWrite和expireAfterAccess同时存在时，以expireAfterWrite为准。
 * maximumSize和maximumWeight不可以同时使用
 */
@Configuration
@Slf4j
public class CacheManagerConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ArrayList<CaffeineCache> list = new ArrayList<>();
        for (CachesEnum cacheEnum : CachesEnum.values()) {
            //根据缓存枚举生成CaffeineCache 同时给CaffeineCache设置名称 使用时根据名称找到对应的CaffeineCache
            Cache<Object, Object> objectCache = Caffeine.newBuilder().recordStats()
                    .expireAfterWrite(cacheEnum.getTtl(), TimeUnit.SECONDS)
                    .maximumSize(cacheEnum.getMaxSize())
                    .build();
            list.add(new CaffeineCache(cacheEnum.getCacheName(), objectCache)
            );
        }
        cacheManager.setCaches(list);
        return cacheManager;
    }

    @Bean
    public CacheLoader<Object, Object> cacheLoader() {
        CacheLoader<Object, Object> cacheLoader = new CacheLoader<Object, Object>() {
            @Override
            public Object load(Object key) throws Exception {
                log.info("=>cacheLoader load : {}", key);
                return null;
            }

            // 重写这个方法将oldValue值返回回去，进而刷新缓存
            @Override
            public Object reload(Object key, Object oldValue) throws Exception {
                log.debug("=>cacheLoader load : {}", key);
                return oldValue;
            }
        };
        return cacheLoader;
    }

}
