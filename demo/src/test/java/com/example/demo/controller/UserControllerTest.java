package com.example.demo.controller;

import com.example.demo.service.UserServiceImpl;
import com.example.demo.vo.UserVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class UserControllerTest {

  @Autowired MockMvc mockMvc;

  @MockBean UserServiceImpl userService;

  UserVO userVO;

  @BeforeEach
  public void setUp() {
    userVO = new UserVO("1", "Hugo", "0987654321", 50);
  }

  @Test
  public void addUserTest() throws Exception {
    when(userService.addUser(any(), any(), any(), anyInt())).thenReturn("1");
    this.mockMvc
        .perform(
            post("/user")
                .param("id", "1")
                .param("name", "Hugo")
                .param("tel", "0987654321")
                .param("age", "1"))
        .andExpect(status().isCreated())
        .andExpect(content().string("No.1added"))
        .andDo(print());
  }

  @Test
  public void updateUserTest() throws Exception {
    this.mockMvc
        .perform(
            put("/user/{id}", "1")
                .param("name", "Tommy")
                .param("tel", "0912345678")
                .param("age", "2"))
        .andExpect(status().isCreated())
        .andExpect(content().string("updateUser completed"))
        .andDo(print())
        .andReturn();
  }

  @Test
  public void deleteUserTest() throws Exception {
    mockMvc
        .perform(delete("/user/{id}", "1"))
        .andExpect(status().isOk())
        .andExpect(content().string("deleteUser complete"))
        .andDo(print());
  }

  @Test
  public void queryUserTest() throws Exception {
    when(userService.getUserById("1")).thenReturn(userVO);
    this.mockMvc
        .perform(get("/user/{id}", "1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").exists())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1"))
        .andExpect(jsonPath("$.name").value("Hugo"))
        .andExpect(jsonPath("$.tel").value("0987654321"))
        .andExpect(jsonPath("$.age").value(50))
        .andDo(print());
  }
}
