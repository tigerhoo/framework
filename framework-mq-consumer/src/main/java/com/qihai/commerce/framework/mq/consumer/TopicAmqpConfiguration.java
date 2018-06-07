package com.qihai.commerce.framework.mq.consumer;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.qihai.commerce.framework.mq.TUserTest;
import com.rabbitmq.client.Channel;

/**
 * topic消费者配置
 * 
 * @author zhugj
 * @date 2018年5月24日 下午2:03:29
 * @version 1.0.0
 */
@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class TopicAmqpConfiguration {
    @Bean("topicTest1Container")
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("queneT");
        container.setMessageListener(listener1());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }


    @Bean("topicTest1Listener")
    public ChannelAwareMessageListener listener1(){
        return new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
            	String str = new String(message.getBody());
            	TUserTest userTest = null;
            	try {
            	    userTest = JSON.parseObject(str, TUserTest.class);
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
            	if ("2".equals(userTest.getId())) {
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                    System.err.println("我已经消费2成功了");
            	} else if ("3".equals(userTest.getId())) { //如果有异常决定是否保留消息在队列中,basicNack是保留消息在队列中
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
                    System.err.println("消息重新回到队列");
            	}

            }
        };
    }

}
