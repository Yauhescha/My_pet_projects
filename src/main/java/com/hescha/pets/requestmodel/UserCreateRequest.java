package com.hescha.pets.requestmodel;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String username;
    private String password;
}
