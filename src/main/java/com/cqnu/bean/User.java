package com.cqnu.bean;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String sex;
    private Integer age;
    private String description;
    private Integer followerCount;
    private Integer fansCount;
    private String imageUrl;
}
