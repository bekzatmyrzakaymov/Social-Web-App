package com.example.dem.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRequest {
    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String secondName;
}