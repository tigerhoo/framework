package com.qihai.commerce.framework.mq;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

/**
 * 消息监听
 * 
 * @author zhugj
 * @date 2018年5月16日 下午2:03:29
 * @version 1.0.0
 */
@Component
public class MQAwareListener implements ChannelAwareMessageListener {

    @Resource
    private MessageConverter messageConverter;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void onMessage(Message message, Channel channel) throws IOException {
        //确认消息成功消费
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
