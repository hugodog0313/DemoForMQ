package com.example.demo.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

  @Bean
  public TopicExchange bootExchange() {
    return new TopicExchange("BOOT-EXCHANGE-1", true, false);
  }

  @Bean
  public Queue bootQueue(){
    return new Queue("queue",true);
//    return  new Queue();
  }

}
