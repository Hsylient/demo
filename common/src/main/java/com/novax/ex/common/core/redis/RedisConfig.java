package com.novax.ex.common.core.redis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Description: redis配置
 *
 * @author my.miao
 * @date 2022/7/1 11:28
 */
@Configuration
public class RedisConfig {

    /**
     * 设置Redis序列化方式，默认使用的JDKSerializer的序列化方式，效率低，这里我们使用 FastJsonRedisSerializer
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 创建 RedisTemplate 对象
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置 Key 的序列化 - String 序列化 RedisSerializer.string() => StringRedisSerializer.UTF_8
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // 设置 Value 的序列化 - JSON 序列化 RedisSerializer.json() => GenericJackson2JsonRedisSerializer
//        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
//        redisTemplate.setHashValueSerializer(RedisSerializer.json());
        redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        // 事务支持false不支持
        redisTemplate.setEnableTransactionSupport(false);// 默认false
        // 必须执行这个函数,初始化RedisTemplate
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
