package com.qihai.commerce.framework.redis.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qihai.commerce.framework.redis.JedisRedisClient;
import com.qihai.commerce.framework.redis.JedisRedisClientPool;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * RedisConfig
 * 
 * @author zhugj
 * @date 2018年5月22日 下午2:03:29
 * @version 1.0.0
 */
@Deprecated
//@Configuration
public class RedisConfig {
    //@Value("${redis.host}")
    private String redisHost;
    //@Value("${redis.port}")
    private Integer redisPort;
    //@Value("${redis.pool.maxActive}")
    private Integer redisMaxActive;
    //@Value("${redis.pool.maxIdle}")
    private Integer redisMaxIdle;
    //@Value("${redis.pool.maxWait}")
    private Integer redisMaxWait;
    //@Value("${redis.password}")
    private String password;
    //@Value("${redis.timeout}")
    private Integer timeout;

    //@Bean(value = "jedisRedisClientPool")
    public JedisRedisClient getJedisRedisClient() {
    	JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(this.redisMaxActive);
		config.setMaxIdle(this.redisMaxIdle);
		config.setMaxWaitMillis(this.redisMaxWait);
        JedisPool jedisPool;
        if (StringUtils.isNotBlank(password)) {
        	jedisPool = new JedisPool(config, redisHost, redisPort, timeout, password);
        } else {
        	jedisPool = new JedisPool(config, redisHost, redisPort);
        }
        JedisRedisClientPool jedisRedisClient = new JedisRedisClientPool();
        jedisRedisClient.setJedisPool(jedisPool);
        return jedisRedisClient;
    }
}
