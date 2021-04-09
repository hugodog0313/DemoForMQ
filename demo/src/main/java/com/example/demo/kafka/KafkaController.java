package com.example.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {


  @Autowired
  Producer producer;

  @PostMapping("/producer")
  public String producer(@RequestParam String message) {
    producer.sendMessages(message);
    return "Message sent to the Kafka Topic java_in_use_topic Successfully";
  }
}
