package com.qihai.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.qihai.commerce.framework.redis.JedisRedisClient;
import com.qihai.commerce.framework.redis.utils.RedisUtils;

//* created by 洪楚洲 on 2017/12/11.


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RedisTest {

    @Autowired
    private JedisRedisClient jedisRedisClient;
    
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testRedisClient() {
    	jedisRedisClient.set("mytest3", "jedisClient3");
        String string = jedisRedisClient.get("mytest3");
        System.out.println(string);
        String mytest3 = jedisRedisClient.get("mytest3");
        System.out.println(mytest3);

    }
    
    @Test
    public void testRedisRedisUtils() {
    	redisUtils.set("mytest3", "jedisClient3");
        String string = (String)redisUtils.get("mytest3");
        System.out.println(string);

    }

}
