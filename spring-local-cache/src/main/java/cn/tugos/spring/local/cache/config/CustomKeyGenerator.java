package cn.tugos.spring.local.cache.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.StringJoiner;


/**
 * @author qinming
 * @date 2021-02-18 17:10:37
 * <p> 自定义生成缓存key </p>
 */
@Component
@Slf4j
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object o, Method method, Object... params) {
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String paramHash = String.valueOf(Arrays.toString(params).hashCode());
        String cacheKey = new StringJoiner("_").add(className).add(methodName)
                .add(paramHash).toString();
        log.debug("generate cache key : {}", cacheKey);
        return cacheKey;
    }
}
