package com.qihai.commerce.framework.redis.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.qihai.commerce.framework.redis.utils.EntityRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * sentinel配置
 * 
 * @author zhugj
 * @date 2018年5月23日 下午2:03:29
 * @version 1.0.0
 */
@Configuration
@EnableAutoConfiguration
public class RedisSentinelConfig {

    @Value("${redis.sentinel.ips}")
    private String redisHosts;
    @Value("${redis.master.name}")
    private String master;
    @Value("${redis.pool.maxIdle:300}")
    private int maxIdle;
    @Value("${redis.pool.maxActive:600}")
    private int maxTotal;
    @Value("${redis.pool.maxWait:3000}")
    private int maxWaitMillis;
    @Value("${redis.pool.testOnBorrow:true}")
    private boolean testOnBorrow;
    @Value("${redis.password}")
    private String password;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }
    
    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration(){
    	RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
    	String[] host = redisHosts.split(",");
    	for(String redisHost : host){
    		String[] item = redisHost.split(":");
    		String ip = item[0];
    		String port = item[1];
    		redisSentinelConfiguration.addSentinel(new RedisNode(ip, Integer.parseInt(port)));
    	}
    	if (StringUtils.isNotBlank(password)) {
    		redisSentinelConfiguration.setPassword(RedisPassword.of(password));
    	}
    	redisSentinelConfiguration.setMaster(master);
    	return redisSentinelConfiguration;
    }

    @Autowired
    private JedisPoolConfig jedisPoolConfig;
    
    @Autowired
    private RedisSentinelConfiguration redisSentinelConfiguration;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
    	JedisConnectionFactory jedisConnectionFactory = 
    			new JedisConnectionFactory(redisSentinelConfiguration, jedisPoolConfig);
    	return jedisConnectionFactory;
    }

    @Lazy(true)
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;
    
    @SuppressWarnings("unchecked")
	@Bean
    public RedisTemplate<?,?> redisTemplate() {
        @SuppressWarnings("rawtypes")
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new EntityRedisSerializer());
        redisTemplate.setValueSerializer(new EntityRedisSerializer());
        redisTemplate.afterPropertiesSet();
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

}
