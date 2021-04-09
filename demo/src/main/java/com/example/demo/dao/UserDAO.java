package com.example.demo.dao;

import com.example.demo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository("userDAO")
public interface UserDAO {
    Optional<UserVO> findByUserId(@Param("id") String id);

    int add(UserVO userVO);

    int updateByUserId(UserVO userVO);

    int deleteByUserId(@Param("id") String id);
}
