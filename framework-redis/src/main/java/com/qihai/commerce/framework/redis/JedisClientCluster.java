package com.qihai.commerce.framework.redis;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisCluster;

/**
 * JedisClientCluster
 * 
 * @author zhugj
 * @date 2018年5月22日 下午2:03:29
 * @version 1.0.0
 */
public class JedisClientCluster implements JedisClient {

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	private JedisCluster jedisCluster;
	

	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	public void setJedisCluster(JedisCluster jedisCluster) {

		this.jedisCluster = jedisCluster;
	}


	@Override
	public long setNx(String key, String value) {
		return jedisCluster.setnx(key, value);
	}

	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public Boolean exists(String key) {
		return jedisCluster.exists(key);
	}

	@Override
	public Long expire(String key, int seconds) {
		return jedisCluster.expire(key, seconds);
	}

	@Override
	public Long ttl(String key) {
		return jedisCluster.ttl(key);
	}

	@Override
	public Long incr(String key) {
		return jedisCluster.incr(key);
	}

	@Override
	public Long hset(String key, String field, String value) {
		return jedisCluster.hset(key, field, value);
	}

	@Override
	public String hget(String key, String field) {
		return jedisCluster.hget(key, field);
	}

	@Override
	public Long hdel(String key, String... field) {
		return jedisCluster.hdel(key, field);
	}

	@Override
	public Boolean hexists(String key, String field) {
		return jedisCluster.hexists(key, field);
	}

	@Override
	public List<String> hvals(String key) {
		return jedisCluster.hvals(key);
	}

	@Override
	public Long del(String key) {
		return jedisCluster.del(key);
	}

	@Override
	public <T> T getPojoByKey(String key, Class<T> beanType)throws Exception{
		try {
			String jsonData = jedisCluster.get(key);
			if(StringUtils.isEmpty(jsonData)){
				return null;
			}
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			throw new Exception("获取缓存时，出现异常");
		}
	}

}
