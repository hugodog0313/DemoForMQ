package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {

  @Autowired UserService userService;
  @Autowired JavaMailSender mailSender;
  @Autowired private AmqpTemplate amqpTemplate;

  @PostMapping("/user")
  public ResponseEntity<String> addUser(
      @RequestParam String id,
      @RequestParam String name,
      @RequestParam String tel,
      @RequestParam int age) {
    String user = userService.addUser(id, name, tel, age);
    if (user != null) {
      //            return "No."+user+"added";
      return new ResponseEntity<>("No." + user + "added", HttpStatus.CREATED);
    }
    return new ResponseEntity<>("id existed", HttpStatus.CREATED);
  }

  @PutMapping("/user/{id}")
  public ResponseEntity<String> updateUser(
      @PathVariable("id") String id,
      @RequestParam String name,
      @RequestParam String tel,
      @RequestParam int age) {
    userService.updateUserById(id, name, tel, age);
    //        return "updateUser completed";
    return new ResponseEntity<>("updateUser completed", HttpStatus.CREATED);
  }

  @PatchMapping("/user/{id}")
  public ResponseEntity<String> updateUserByPatch(
      @PathVariable("id") String id,
      @RequestParam String name,
      @RequestParam String tel,
      @RequestParam int age) {
    userService.updateUserById(id, name, tel, age);
    //        return "updateUser completed";
    return new ResponseEntity<>("updateUser completed", HttpStatus.CREATED);
  }

  @DeleteMapping("/user/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
    userService.deleteUserById(id);
    //        return "deleteUser complete";
    return new ResponseEntity<>("deleteUser complete", HttpStatus.OK);
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<UserVO> queryUser(@PathVariable("id") String id) {
    UserVO userVO = userService.getUserById(id);
    //        return userVO;
    UserVO user = new UserVO();
    user.setId(userVO.getId());
    user.setName(userVO.getName());
    user.setTel(userVO.getTel());
    user.setAge(userVO.getAge());
    Gson gson = new Gson();
    amqpTemplate.convertAndSend("queue", gson.toJson(user));
    return new ResponseEntity<>(userVO, HttpStatus.OK);
  }

  @GetMapping("/test")
  public String test() {
    return "123";
  }

  //  @RequestMapping(value = "/mail")
  //  public void sendSimpleMail() {
  //    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
  //    simpleMailMessage.setFrom("eeit124group1@gmail.com");
  //    simpleMailMessage.setTo("hugo.huang.y.h@chowsangsang.com");
  //    simpleMailMessage.setSubject("MQ TEST");
  //    simpleMailMessage.setText("MQ測試test");
  //    mailSender.send(simpleMailMessage);
  //  }
}
