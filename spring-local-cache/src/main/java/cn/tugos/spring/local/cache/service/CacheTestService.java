package cn.tugos.spring.local.cache.service;

import cn.hutool.core.util.RandomUtil;
import cn.tugos.spring.local.cache.config.CacheName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author qinming
 * @date 2021-02-18 17:29:08
 * <p> 测试缓存 </p>
 */
@Slf4j
@Service
public class CacheTestService {

    @Cacheable(value = CacheName.COMMON_CACHE, keyGenerator = "customKeyGenerator")
    public String getName(Integer id) {
        log.info("getName未使用缓存：{}", id);
        return RandomUtil.randomNumbers(10) + "---" + id;
    }

    @Cacheable(value = CacheName.USER_CACHE, keyGenerator = "customKeyGenerator")
    public int getAge(Integer id) {
        log.info("getAge未使用缓存：{}", id);
        return RandomUtil.randomInt();
    }

}
