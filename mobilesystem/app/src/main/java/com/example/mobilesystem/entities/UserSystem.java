package com.example.mobilesystem.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserSystem {
    private String name_user,password;

    public UserSystem(String name_user, String password) {
        this.name_user = name_user;
        this.password = password;
    }

    public UserSystem() {

    }

    public String getName_user() {
        return name_user;
    }

    public String getPassword() {
        return password;
    }

    public static String Json(UserSystem userSystem) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(userSystem);
    }
}
