package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisConfig {
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        return new RedisTemplate<>() {{
            setKeySerializer(new StringRedisSerializer());
            setValueSerializer(new GenericToStringSerializer<>(Object.class));
            setHashValueSerializer(new GenericToStringSerializer<>(Object.class));
            setConnectionFactory(jedisConnectionFactory);
        }};
    }
}
