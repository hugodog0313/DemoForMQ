package com.example.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
  private static final Logger logger = LoggerFactory.getLogger(Producer.class);
  private static final String TOPIC = "advice-topic";

  @Autowired KafkaTemplate kafkaTemplate;

  public void sendMessages(String message) {
    //    kafkaTemplate.send(TOPIC, message);
    //    kafkaTemplate.send(TOPIC, "advice-topic3", message);
    kafkaTemplate.send(TOPIC, 2, "advice-topic3", message);
    //    System.out.println("send"+message);
  }
}
