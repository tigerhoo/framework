package com.qihai.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.qihai.commerce.framework.mq.producer.RabbitMqSender;


/**
 * 消息生产测试
 * @author zhuguojin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestSendMsg {

	@Autowired
	private RabbitMqSender rabbitMqSender;
	
    @Test
    public void test() {
    	TUserTest user = new TUserTest("1","zhuguojin");
    	rabbitMqSender.sendRabbitmqDirect("TESTQUEUE1", user);
    	/*
    	rabbitMqSender.sendRabbitmqDirect("TESTQUEUE1", "1");
    	rabbitMqSender.sendRabbitmqTopic("*.TEST.*", "2");
    	rabbitMqSender.sendRabbitmqTopic("lazy.#","3");
    	*/
    }
    
}
