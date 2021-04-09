package com.example.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

  @KafkaListener(topics = "advice-topic", groupId = "group_id")
  public void consume(String message) {
    System.out.println("send"+":"+message);
  }
}
