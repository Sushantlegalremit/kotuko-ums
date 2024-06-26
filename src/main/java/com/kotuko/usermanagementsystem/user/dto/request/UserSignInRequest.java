package com.kotuko.usermanagementsystem.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInRequest {
    private String username;
    private String password;
}
