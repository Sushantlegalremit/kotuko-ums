package com.kotuko.usermanagementsystem.user.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String dob;
}
