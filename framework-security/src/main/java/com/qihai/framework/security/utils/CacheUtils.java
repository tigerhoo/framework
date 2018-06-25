package com.qihai.framework.security.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.qihai.commerce.framework.utils.SpringContextsUtil;

/**
 * Created by liheng on 2017/5/20.
 */
public class CacheUtils {
    private static RedisTemplate<String, Object> cacheTemplate;

    public static long TIME_OUT_MONTH = 31 * 24 * 60 * 60;
    public static long TIME_OUT_WEEK = 7 * 24 * 60 * 60;
    public static long TIME_OUT_DAY = 24 * 60 * 60;
    public static long TIME_OUT_TWO_HOUR = 2 * 60 * 60;
    public static long TIME_OUT_HOUR = 1 * 60 * 60;
    public static long TIME_OUT_HALF_HOUR = 30 * 60;

    public static <T> T get(String key, Class<T> theClass) {
        return (T) getCacheTemplate().opsForValue().get(key);
    }


    public static boolean setBit(String key, long offset, boolean value) {
        return CacheUtils.setBit(key, offset, value,TIME_OUT_WEEK);
    }

    public static boolean setBit(String key, long offset) {
        return CacheUtils.setBit(key, offset, true);
    }


    public static boolean setBit(String key, long offset, boolean value,long timeout) {
        boolean result = getCacheTemplate().opsForValue().setBit(key, offset, value);
        expireBySeconds(key, timeout);
        return result;
    }

    public static boolean hashKey(String key){
        return getCacheTemplate().hasKey(key);
    }

    public static void delKeys(List<String> keys){
        getCacheTemplate().delete(keys);
    }

    public static void delKey(String key){
        getCacheTemplate().delete(key);
    }


    public static boolean getBit(String key, long offset) {
        return getCacheTemplate().opsForValue().getBit(key, offset);
    }

    public static Object get(String key) {
        return getCacheTemplate().opsForValue().get(key);
    }

    public static void set(String key, Object data) {
        getCacheTemplate().opsForValue().set(key, data, TIME_OUT_WEEK);
    }

    public static void set(String key, Object data, long timeout) {
        getCacheTemplate().opsForValue().set(key, data, timeout, TimeUnit.SECONDS);
    }

    public static List multiGet(List ids) {
        return getCacheTemplate().opsForValue().multiGet(ids);
    }

    public static void multiSet(Map<String, Object> dataMap) {
        getCacheTemplate().opsForValue().multiSet(dataMap);
    }

    public static void delete(String... keys) {
        List<String> result = new ArrayList<>();
        for (String key : keys) {
            if (key != null) {
                result.add(key);
            }
        }
        getCacheTemplate().delete(result);
    }


    public static boolean isMember4Set(String key, String member) {
        return getCacheTemplate().opsForSet().isMember(key, member);
    }

    public static void add4Set(String key, String member, long timeout) {
        getCacheTemplate().opsForSet().add(key, member);
        expireBySeconds(key, timeout);
    }


    public static boolean exist4Hash(String key, String field) {
        return getCacheTemplate().opsForHash().hasKey(key, field);
    }

    public static Object get4Hash(String key, String field) {
        return getCacheTemplate().opsForHash().get(key, field);
    }

    public static List<Object> gets4Hash(String key, String... fields) {
        return getCacheTemplate().opsForHash().multiGet(key, Arrays.asList(fields));
    }

    public static void put4Hash(String key, String field, Object value, long timeout) {
        getCacheTemplate().opsForHash().put(key, field, value);
        expireBySeconds(key, timeout);
    }

    public static void putAll4Hash(String key, Map<String, Object> values, long timeout) {
        getCacheTemplate().opsForHash().putAll(key, values);
        expireBySeconds(key, timeout);
    }

    public static void expireBySeconds(String key, final long timeout) {
        expire(key, timeout, TimeUnit.SECONDS);
    }

    public static void expire(String key, final long timeout, final TimeUnit unit) {

        getCacheTemplate().expire(key, timeout, unit);
    }

    public static void expireBatch(final String[] keys, final long timeout) {
        if (keys == null || keys.length < 1) {
            return;
        }

        /*
        getCacheTemplate().execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] rawKey = null;
                for (String key : keys) {
                    rawKey = getCacheTemplate().getStringSerializer().serialize(key);
                    connection.expire(rawKey, timeout);
                }
                return null;
            }
        });
        */

        getCacheTemplate().executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] rawKey = null;
                for (String key : keys) {
                    rawKey = getCacheTemplate().getStringSerializer().serialize(key);
                    connection.expire(rawKey, timeout);
                }
                return null;
            }
        });
    }

    public static Long increment(String key) {
        return getCacheTemplate().opsForValue().increment(key, 1);
    }

    public static Long increment(String key, long delta) {
        return getCacheTemplate().opsForValue().increment(key, delta);
    }

    public static Long decrement(String key) {
        return increment(key, -1);
    }

    public static Long decrement(String key, long delta) {
        return increment(key, -delta);
    }

    public static RedisTemplate<String, Object> getCacheTemplate() {
        if (cacheTemplate == null) {
            cacheTemplate = (RedisTemplate<String, Object>)SpringContextsUtil.getBean("redisTemplate", RedisTemplate.class);
        }
        return cacheTemplate;
    }
}
