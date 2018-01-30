package com.urwoo.redis.impl;

import com.urwoo.redis.JedisClient;
import lombok.Setter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Setter
public class JedisClientSingle implements JedisClient {

    private JedisPool jedisPool;

    private Jedis getResource() {
        Jedis resource = jedisPool.getResource();
        return resource;
    }

    @Override
    public String get(String key) {

        Jedis resource = getResource();
        String string = resource.get(key);
        resource.close();
        return string;
    }

    @Override
    public String set(String key, String value) {

        Jedis resource = getResource();
        String string = resource.set(key, value);
        resource.close();
        return string;
    }

    @Override
    public String hget(String hkey, String key) {

        Jedis resource = getResource();
        String string = resource.hget(hkey, key);
        resource.close();
        return string;
    }

    @Override
    public long hset(String hkey, String key, String value) {

        Jedis resource = getResource();
        Long hset = resource.hset(hkey, key, value);
        resource.close();
        return hset;
    }

    @Override
    public long incr(String key) {

        Jedis resource = getResource();
        Long incr = resource.incr(key);
        resource.close();
        return incr;

    }

    @Override
    public long expire(String key, Integer second) {

        Jedis resource = getResource();
        Long expire = resource.expire(key, second);
        resource.close();
        return expire;

    }

    @Override
    public long ttl(String key) {

        Jedis resource = getResource();
        Long ttl = resource.ttl(key);
        resource.close();
        return ttl;
    }

    @Override
    public long del(String key) {

        Jedis resource = getResource();
        Long del = resource.del(key);
        resource.close();
        return del;
    }

    @Override
    public long hdel(String hkey, String key) {

        Jedis resource = getResource();
        Long hdel = resource.hdel(hkey, key);
        resource.close();
        return hdel;
    }

    @Override
    public Set<String> keys(String pattern) {
        Jedis resource = getResource();
        Set<String> sets = resource.keys(pattern);
        resource.close();
        return sets;
    }
}
