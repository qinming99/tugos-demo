package cn.tugos.spring.redis.demo.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ObjectUtils;

import java.time.Duration;

/**
 * @author qinming
 * @date 2021-01-22 15:11:10
 * <p> redis多数据源配置 </p>
 */
@Configuration
public class RedisConfig {

    @Bean(name = "redisTemplate")
    @Primary
    public RedisTemplate<String, Object> redisTemplate(@Value("${spring.redis.host}") String host,
                                                       @Value("${spring.redis.port}") int port,
                                                       @Value("${spring.redis.password}") String password,
                                                       @Value("${spring.redis.database}") int database,
                                                       @Value("${spring.redis.timeout}") long timeout,
                                                       @Value("${spring.redis.lettuce.pool.max-active}") int maxActive,
                                                       @Value("${spring.redis.lettuce.pool.max-wait}") int maxWait,
                                                       @Value("${spring.redis.lettuce.pool.max-idle}") int maxIdle,
                                                       @Value("${spring.redis.lettuce.pool.min-idle}") int minIdle) {
        LettuceConnectionFactory connectionFactory = this.lettuceConnectionFactory(host, port, password, database,
                timeout, maxActive, maxWait, maxIdle, minIdle);
        return this.createRedisTemplate(connectionFactory);
    }


    @Bean(name = "redisTemplate2")
    public RedisTemplate<String, Object> redisTemplate2(@Value("${spring.redis.host2}") String host,
                                                        @Value("${spring.redis.port2}") int port,
                                                        @Value("${spring.redis.password2}") String password,
                                                        @Value("${spring.redis.database2}") int database,
                                                        @Value("${spring.redis.timeout2}") long timeout,
                                                        @Value("${spring.redis.lettuce.pool.max-active}") int maxActive,
                                                        @Value("${spring.redis.lettuce.pool.max-wait}") int maxWait,
                                                        @Value("${spring.redis.lettuce.pool.max-idle}") int maxIdle,
                                                        @Value("${spring.redis.lettuce.pool.min-idle}") int minIdle) {
        LettuceConnectionFactory connectionFactory = this.lettuceConnectionFactory(host, port, password, database,
                timeout, maxActive, maxWait, maxIdle, minIdle);
        return this.createRedisTemplate(connectionFactory);
    }


    /**
     * 创建RedisTemplate并设置序列号
     *
     * @param redisConnectionFactory redis连接池
     */
    private RedisTemplate<String, Object> createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //序列号
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    /**
     * lettuce 连接池工厂
     *
     * @param host      地址
     * @param port      端口
     * @param password  密码
     * @param database  数据库
     * @param timeout   连接超时时间（毫秒）
     * @param maxActive 连接池最大连接数（使用负值表示没有限制） 默认 8
     * @param maxWait   连接池最大阻塞等待时间（使用负值表示没有限制），当连接池资源耗尽时，调用者最大阻塞的时间，超时将跑出异常。单位，毫秒数;默认为-1.表示永不超时.
     * @param maxIdle   连接池中的最大空闲的连接数,默认为8
     * @param minIdle   连接池中的最少空闲的连接数,默认为0
     */
    public LettuceConnectionFactory lettuceConnectionFactory(String host, int port, String password, int database,
                                                             long timeout, int maxActive, int maxWait, int maxIdle,
                                                             int minIdle) {
        //基础设置
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setDatabase(database);
        if (!ObjectUtils.isEmpty(password)) {
            configuration.setPassword(password);
        }
        configuration.setPort(port);
        //线程池设置
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().
                commandTimeout(Duration.ofSeconds(timeout)).
                poolConfig(poolConfig).build();
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(configuration, clientConfiguration);
        lettuceConnectionFactory.setShareNativeConnection(true);
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }

}
