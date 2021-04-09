package com.example.demo.vo;

public class UserVO {


  private String id;
  private String name;
  private String tel;
  private Integer age;

  public UserVO() {}

  public UserVO(String id, String name, String tel, int age) {
    this.setId(id);
    this.setName(name);
    this.setTel(tel);
    this.setAge(age);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
