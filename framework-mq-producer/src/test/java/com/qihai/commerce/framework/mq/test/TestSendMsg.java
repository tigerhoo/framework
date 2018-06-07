package com.qihai.commerce.framework.mq.test;

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
    	boolean isTopic = true;
    	if (!isTopic) {
	    	//direct测试
	    	TUserTest user = new TUserTest("1","zhuguojin sending message");
	    	rabbitMqSender.sendRabbitmqDirect("exchangeD","routingkeyD","queneD", user);
    	} else {
    		TUserTest user = new TUserTest("2","zhuguojin sending topic message");
	    	rabbitMqSender.sendRabbitmqTopic("exchangeT","routingkeyT","queneT", user);
    	}
    	
    	//rabbitMqSender.sendRabbitmqTopic("exchangeT","routingkeyT", user);
    	/*
    	rabbitMqSender.sendRabbitmqDirect("TESTQUEUE1", "1");
    	rabbitMqSender.sendRabbitmqTopic("*.TEST.*", "2");
    	rabbitMqSender.sendRabbitmqTopic("lazy.#","3");
    	*/
    }
    
}
