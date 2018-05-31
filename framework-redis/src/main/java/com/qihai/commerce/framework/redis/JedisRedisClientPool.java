package com.qihai.commerce.framework.redis;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * JedisReadClientPool
 * 
 * @author zhugj
 * @date 2018年5月22日 下午2:03:29
 * @version 1.0.0
 */
public class JedisRedisClientPool implements JedisRedisClient {

    private JedisPool jedisPool;

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public long setNx(String key, String value) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.setnx(key, value);
        }

    }

    @Override
    public String set(String key, String value) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.set(key, value);
        }

    }

    @Override
    public String get(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    @Override
    public Boolean exists(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        }
    }

    @Override
    public Long expire(String key, int seconds) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.expire(key, seconds);
        }
    }

    @Override
    public Long ttl(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.ttl(key);
        }
    }

    @Override
    public Long incr(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.incr(key);
        }
    }

    @Override
    public Long hset(String key, String field, String value) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.hset(key, field, value);
        }
    }

    @Override
    public String hget(String key, String field) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.hget(key, field);
        }
    }

    @Override
    public Long hdel(String key, String... field) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.hdel(key, field);
        }
    }

    @Override
    public Boolean hexists(String key, String field) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.hexists(key, field);
        }
    }

    @Override
    public List<String> hvals(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.hvals(key);
        }
    }

    @Override
    public Long del(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.del(key);
        }
    }

    @Override
    public <T> T getPojoByKey(String key, Class<T> beanType) throws Exception {
        Jedis jedis = jedisPool.getResource();
        try {
            String jsonData = jedis.get(key);
            if(StringUtils.isEmpty(jsonData)){
                return null;
            }
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            throw new Exception("获取缓存时，出现异常");
        } finally {
            jedis.close();
        }
    }

}
