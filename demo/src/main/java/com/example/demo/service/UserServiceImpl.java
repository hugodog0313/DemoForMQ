package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public String addUser(String id, String tel, String name, int age) {//return userId
        Optional<UserVO> user = userDAO.findByUserId(id);
        if (user.isEmpty()) {
            UserVO userVO = new UserVO(id, tel, name, age);
            userDAO.add(userVO);
            return userVO.getId();
        }
        return null;
    }

    @Override
    public UserVO getUserById(String id) {
        Optional<UserVO> optionalUserVO = userDAO.findByUserId(id);
        if (optionalUserVO.isEmpty()) {
            return null;
        }
        UserVO userVO = optionalUserVO.get();
        return userVO;
    }

    @Override
    public void deleteUserById(String id) {
        Optional<UserVO> optionalUserVO = userDAO.findByUserId(id);
        if (!optionalUserVO.isEmpty()) {
            int i = userDAO.deleteByUserId(id);
        }
    }

    @Override
    public void updateUserById(String id, String name, String tel, int age) {
        Optional<UserVO> optionalUserVO = userDAO.findByUserId(id);
        UserVO userVO = optionalUserVO.get();
        if (!optionalUserVO.isEmpty()) {
            userVO.setName(name);
            userVO.setTel(tel);
            userVO.setAge(age);
        }
        userDAO.updateByUserId(userVO);
    }
}
