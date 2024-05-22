package com.kotuko.usermanagementsystem.helper;

import com.kotuko.usermanagementsystem.user.dto.request.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {
    public static UserRequest createUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("kotuko@gmail.com");
        userRequest.setPassword("password");
        userRequest.setFirstName("Kotuko");
        userRequest.setLastName("Kotuko");
        userRequest.setRoles(null);
        userRequest.setDateOfBirth("2010-09-10");
        userRequest.setUsername("kotuko");
        return userRequest;
    }
}
