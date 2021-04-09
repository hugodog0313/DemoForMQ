package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.vo.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Mock
    UserDAO userDAO;

    @InjectMocks
    UserServiceImpl userService;

    UserVO userVO;

    @BeforeEach
    public void setUp() {
        String id = "1";
        String name = "Aluba";
        String tel = "0987654321";
        int age = 55;
        userVO = new UserVO(id, name, tel, age);
    }

    @Test
    public void addUserTest() {
        String userId = userService.addUser("50", "0912345678", "Hugo"
                , 55);
        System.out.println("added" + userId);
        Assertions.assertNotNull(userId);
    }

    @Test
    public void getUserByIdTest() {
        when(userDAO.findByUserId("1")).thenReturn(java.util.Optional.of(new UserVO("1", "Aluba", "0987654321", 55)));
        UserVO user = userService.getUserById("1");
        Assertions.assertEquals(user.getId(),userVO.getId());
    }

    @Test
    public void deleteUserByIdTest(){
        when(userDAO.findByUserId("1")).thenReturn(java.util.Optional.of(new UserVO("1", "Aluba", "0987654321", 55)));
        userService.deleteUserById("1");
        verify(userDAO,times(1)).deleteByUserId("1");
    }

    @Test
    public void updateUserById() {
        UserVO user = new UserVO("1", "Aluba", "0987654321", 55);
        when(userDAO.findByUserId("1")).thenReturn(java.util.Optional.of(user));
        userService.updateUserById("1", "Aluba", "0987654321", 55);
        verify(userDAO,times(1)).updateByUserId(user);
    }
}
