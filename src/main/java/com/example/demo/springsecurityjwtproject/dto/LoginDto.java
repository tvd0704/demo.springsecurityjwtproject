package com.example.demo.springsecurityjwtproject.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String userName;
    private String password;
}
