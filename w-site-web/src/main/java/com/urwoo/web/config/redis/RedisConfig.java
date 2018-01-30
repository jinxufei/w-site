package com.urwoo.web.config.redis;

import com.urwoo.redis.JedisClient;
import com.urwoo.redis.impl.JedisClientSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class RedisConfig {

    @Autowired
    RedisSingleProperties redisSingleProperties;

    @Bean
    public JedisPool jedisPool(){
        JedisPool jedisPool = new JedisPool(
                redisSingleProperties.getSingleHost(),
                redisSingleProperties.getSinglePort());
        return jedisPool;
    }

    @Bean
    public JedisClient jedisClient(JedisPool jedisPool){
        if (redisSingleProperties.isCluster()){
            return null;
        }else {
            JedisClientSingle jedisClient = new JedisClientSingle();
            jedisClient.setJedisPool(jedisPool);
            return jedisClient;
        }
    }
}
