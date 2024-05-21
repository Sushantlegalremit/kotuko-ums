package com.kotuko.usermanagementsystem.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String dateOfBirth;
    private String password;
    private Set<RoleRequest> roles;
}
