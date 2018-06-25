package com.qihai.commerce.framework.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qihai.commerce.framework.Application;
import com.qihai.commerce.framework.redis.utils.RedisUtils;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class RedisTest {

   /* @Autowired
    private JedisRedisClient jedisRedisClient;*/
    
    @Autowired
    private RedisUtils redisUtils;

    /*@Test
    public void testRedisClient() {
    	jedisRedisClient.set("mytest3", "jedisClient3");
        String string = jedisRedisClient.get("mytest3");
        System.out.println(string);
        String mytest3 = jedisRedisClient.get("mytest3");
        System.out.println(mytest3);

    }*/
    
    @Test
    public void testRedisRedisUtils() {
    	redisUtils.set("mytest3", "jedisClient3");
        String string = (String)redisUtils.get("mytest3");
        System.out.println(string);

    }

}
