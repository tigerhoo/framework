package com.qihai.commerce.framework.mq;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.qihai.commerce.framework.mq.utils.JsonMapper;

/**
 * 基础实体对象
 * 
 * @author zhugj
 * @date 2017年8月17日 下午2:03:29
 * @version 1.0.0
 */
@Service
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class BasicService implements ConfirmCallback {

    @Resource
    public RabbitTemplate rabbitTemplate;

    private String exchange;

    private String routingKey;

    public void sendMessage(final String correlationId, Object request) {
    	
    	String content = JsonMapper.getInstance().toJson(request);

        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setCorrelationKey(correlationId);
        rabbitTemplate.convertAndSend(exchange, routingKey, content, new MessagePostProcessor() {  
            //设置消息的通用属性
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setTimestamp(new Date());
                return message;
            }
        }, new CorrelationData(correlationId));
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("回调id:" + correlationData);
        if (ack) {
            System.out.println("消息发送成功");
        } else {
            System.out.println("消息发送失败:" + cause);
        }
    }

}

