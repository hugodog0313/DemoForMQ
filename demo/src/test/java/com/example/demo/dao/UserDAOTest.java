package com.example.demo.dao;

import com.example.demo.vo.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.util.Optional;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ContextConfiguration("application.yml")
@ActiveProfiles("test")
public class UserDAOTest {
    @Autowired
    UserDAO userDAO;
    @Autowired
    DataSource dataSource;
    UserVO userVO;

    @BeforeEach
    public  void  setUp(){
        String id = "3";
        String name= "Hugo";
        String tel = "0987654321";
        Integer age = 50;
        userVO = new UserVO(id,name,tel,age);
    }

    @Test
    public void add(){
        int i = userDAO.add(userVO);
        Assertions.assertEquals(i,1);
    }

    @Test
    public void findByUserId(){
//        userDAO.add(userVO);
        Optional<UserVO> optional = userDAO.findByUserId("2");
        UserVO userVO = optional.get();
        System.out.println(userVO.getId()+userVO.getName());
        Assertions.assertNotNull(userVO);
    }

    @Test
    public void delete(){
        userDAO.add(userVO);
        int i = userDAO.deleteByUserId("3");
        Optional<UserVO> optional = userDAO.findByUserId("3");
        Assertions.assertTrue(optional.isEmpty());
        Assertions.assertEquals(i,1);
    }

    @Test
    public  void updateByUseId(){
        userDAO.add(userVO);
        userVO.setName("Aluba");
        userVO.setTel("0912345678");
        userVO.setAge(100);
        int i = userDAO.updateByUserId(userVO);
        Assertions.assertEquals(i,1);
        Assertions.assertEquals("Aluba",userVO.getName());
        Assertions.assertEquals("Aluba",userVO.getName());
    }
}
