package cn.tugos.spring.redis.demo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author qinming
 * @date 2021-01-22 19:52:40
 * <p> 无 </p>
 */
@Service
public class TestRedis {

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;


    @Resource(name = "redisTemplate2")
    private RedisTemplate redisTemplate2;


    @PostConstruct
    public void test(){
        redisTemplate.opsForValue().set("redis1","hello redis1");
        Object redis1 = redisTemplate.opsForValue().get("redis1");
        System.out.println(redis1);

        redisTemplate2.opsForValue().set("redis2","hello redis2");
        Object redis2 = redisTemplate2.opsForValue().get("redis2");
        System.out.println(redis2);
    }

}
