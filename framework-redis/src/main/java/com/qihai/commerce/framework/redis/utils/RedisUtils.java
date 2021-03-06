package com.qihai.commerce.framework.redis.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

/**
 * RedisUtils工具类
 * 
 * @author zhugj
 * @date 2018年5月22日 下午2:03:29
 * @version 1.0.0
 */
@Service
public class RedisUtils {
    @SuppressWarnings("rawtypes")
	@Autowired
    private RedisTemplate redisTemplate;
    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 写入缓存设置时效时间
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     * @param pattern
     */
    @SuppressWarnings("unchecked")
	public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }
    /**
     * 删除对应的value
     * @param key
     */
    @SuppressWarnings("unchecked")
	public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
	public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * 读取缓存
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
	public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }
    /**
     * 哈希 添加
     * @param key
     * @param hashKey
     * @param value
     */
    @SuppressWarnings("unchecked")
	public void hmSet(String key, Object hashKey, Object value, Long expireTime){
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
        if (expireTime != null) {
           redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    @SuppressWarnings("unchecked")
	public Object hmGet(String key, Object hashKey){
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }

    /**
     * 列表添加
     * @param k
     * @param v
     */
    @SuppressWarnings("unchecked")
	public void lPush(String k,Object v, Long expireTime){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k,v);
        if (expireTime != null) {
            redisTemplate.expire(k, expireTime, TimeUnit.SECONDS);
         }
    }

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Object> lRange(String k, long l, long l1){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k,l,l1);
    }

    /**
     * 集合添加
     * @param key
     * @param value
     */
    @SuppressWarnings("unchecked")
	public void add(String key, Object value, Long expireTime){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key,value);
        if (expireTime != null) {
           redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    /**
     * 集合获取
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
	public Set<Object> setMembers(String key){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    @SuppressWarnings("unchecked")
	public void zAdd(String key,Object value, double scoure, Long expireTime){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key,value,scoure);
        if (expireTime != null) {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    @SuppressWarnings("unchecked")
	public Set<Object> rangeByScore(String key,double scoure,double scoure1){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }
}
