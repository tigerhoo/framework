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
import com.qihai.TUserTest;
import com.rabbitmq.client.Channel;

/**
 * 直连消费者配置
 * 
 * @author zhugj
 * @date 2018年5月24日 下午2:03:29
 * @version 1.0.0
 */
@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class DirectAmqpConfiguration {
	
    @Bean("testQueueContainer")
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("TESTQUEUE");
        container.setMessageListener(listener());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }


    @Bean("testQueueListener")
    public ChannelAwareMessageListener listener() {
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
                //通过设置TestUser的name来测试回调，分别发两条消息，一条UserName为1，一条为2，查看控制台中队列中消息是否被消费
                if ("1".equals(userTest.getId())){
                    System.err.println("我已经消费1成功了");
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                }

                if ("2".equals(str)){
                    System.err.println("消息重新回到队列");
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
                }

            }
        };
    }

}
