package com.example.demo.mq;

import com.example.demo.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DemoApplication.class)
public class TestRabbitMQ {

  @Autowired private Sender sender;

  @Test
  public void testRabbit(){
//    sender.send();
  }
}
