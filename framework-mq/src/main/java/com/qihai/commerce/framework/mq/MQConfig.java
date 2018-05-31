package com.qihai.commerce.framework.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 消息配置
 * 
 * @author zhugj
 * @date 2018年5月16日 下午2:03:29
 * @version 1.0.0
 */
@Configuration
public class MQConfig {
	
	@Value("${spring.rabbitmq.queueName}")  
    private String queueName;
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingKey}")
    private String routingKey;
    @Value("${spring.rabbitmq.addresses}")  
    private String addresses;  
    @Value("${spring.rabbitmq.userName}")  
    private String userName;  
    @Value("${spring.rabbitmq.password}")  
    private String password;  
    @Value("${spring.rabbitmq.virtualHost}")  
    private String virtualHost;  
    @Value("${spring.rabbitmq.publisherConfirms}")  
    private boolean publisherConfirms;    
      
      @Bean
      public ConnectionFactory connectionFactory() {
    	  CachingConnectionFactory connectionFactory = new CachingConnectionFactory();  
          connectionFactory.setAddresses(addresses);  
          connectionFactory.setUsername(userName);  
          connectionFactory.setPassword(password);  
          connectionFactory.setVirtualHost(virtualHost);  
          /** 如果要进行消息回调，则这里必须要设置为true */  
          connectionFactory.setPublisherConfirms(publisherConfirms);  
          return connectionFactory;  
      }

      @Bean
      public MessageConverter messageConverter() {
          return new Jackson2JsonMessageConverter();
      }

      @Bean  
      /** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */  
      @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  
      public RabbitTemplate rabbitTemplate() {  
          RabbitTemplate template = new RabbitTemplate(connectionFactory());  
          return template;  
      }  

      @Bean
      public MessageListenerAdapter listenerAdapter(MQAwareListener listener,
              MessageConverter converter) {
          return new MessageListenerAdapter(listener, converter);
      }

      @Bean
      public Queue queue() {
          return new Queue(queueName, true);
      }

      // TopicExchange(*、#模糊匹配routing key，routing
      // key必须包含".")，DirectExchange，FanoutExchange(无routing key概念)
      @Bean
      public TopicExchange exchange() {
          return new TopicExchange(exchange);
      }

      @Bean
      public Binding binding(Queue queue, TopicExchange exchange) {
          return BindingBuilder.bind(queue).to(exchange).with(routingKey);
      }
  }
