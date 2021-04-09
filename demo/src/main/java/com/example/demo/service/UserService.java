package com.example.demo.service;

import com.example.demo.vo.UserVO;

public interface UserService {


    String addUser(String id,String tel,String name, int age);

    UserVO getUserById(String id);

    void deleteUserById(String id);

    void updateUserById(String id,String name,String tel, int age);

}
