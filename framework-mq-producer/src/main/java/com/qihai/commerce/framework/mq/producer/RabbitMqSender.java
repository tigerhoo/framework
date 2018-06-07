package com.qihai.commerce.framework.mq.producer;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * rabbitmq发送消息工具类
 * 
 * @author zhugj
 * @date 2018年5月24日 下午2:03:29
 * @version 1.0.0
 */
@Component
public class RabbitMqSender implements RabbitTemplate.ConfirmCallback{
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqSender.class);

    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        logger.info("confirm: " + correlationData.getId());
    }

    /**
     * 发送到 指定routekey的指定queue
     * @param routeKey
     * @param obj
     */
    public void sendRabbitmqDirect(String exchange, String routeKey, String queueName, Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send: " + correlationData.getId());
        this.rabbitTemplate.setExchange(exchange);
        this.rabbitTemplate.setRoutingKey(routeKey);
        RabbitAdmin admin = new RabbitAdmin(this.rabbitTemplate.getConnectionFactory());
        admin.declareQueue(new Queue(queueName));
        DirectExchange directExchange = new DirectExchange(exchange);
        admin.declareExchange(directExchange);
        Binding binding = BindingBuilder.bind(new Queue(queueName)).to(directExchange).with(routeKey);
        admin.declareBinding(binding);
        this.rabbitTemplate.convertAndSend(exchange, routeKey , obj, correlationData);
    }

    /**
     * 所有发送到Topic Exchange的消息被转发到所有关心RouteKey中指定Topic的Queue上
     * @param routeKey
     * @param obj
     */
    public void sendRabbitmqTopic(String exchange, String routeKey, String queueName, Object obj) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("send: " + correlationData.getId());
        this.rabbitTemplate.setExchange(exchange);
        this.rabbitTemplate.setRoutingKey(routeKey);
        RabbitAdmin admin = new RabbitAdmin(this.rabbitTemplate.getConnectionFactory());
        admin.declareQueue(new Queue(queueName));
        TopicExchange topicExchange = new TopicExchange(exchange);
        admin.declareExchange(topicExchange);
        Binding binding = BindingBuilder.bind(new Queue(queueName)).to(topicExchange).with(routeKey);
        admin.declareBinding(binding);
        this.rabbitTemplate.convertAndSend(exchange, routeKey , obj, correlationData);
    }
}
