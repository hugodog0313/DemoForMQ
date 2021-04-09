package com.example.demo.mq;

import java.time.LocalDateTime;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MQController {

  @Autowired JavaMailSender mailSender;
  @Autowired private AmqpTemplate amqpTemplate;

  @RequestMapping(value = "/mail")
  public void sendSimpleMail() {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom("hugo.huang.y.h@chowsangsang.com");
    simpleMailMessage.setTo("hugo.huang.y.h@chowsangsang.com");
    simpleMailMessage.setSubject("MQ TEST");
    simpleMailMessage.setText("MQ測試test");
    mailSender.send(simpleMailMessage);
  }

  @PostMapping("/message")
  public void sendMessageToReceiver(@RequestParam String message) {
    LocalDateTime localDateTime = LocalDateTime.now();
    amqpTemplate.convertAndSend("queue", localDateTime+","+message);
//    amqpTemplate.convertAndSend("BOOT-EXCHANGE-1","Routing-Key","message");
  }
}
