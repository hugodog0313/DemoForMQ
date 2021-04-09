package com.example.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired private KafkaProperties kafkaProperties;

  private String topicName = "advice-topic";


  @Bean
  public NewTopic adviceTopic() {
    return new NewTopic(topicName, 3, (short) 2);
    // replication-factor 為partition的副本數，每個partition可以有多少個副本，副本位於不同的
    // broker上，所以replication-factor數量不可大於broker的數量。
  }
}
