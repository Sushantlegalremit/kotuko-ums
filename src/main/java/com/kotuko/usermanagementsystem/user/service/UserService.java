package com.kotuko.usermanagementsystem.user.service;

import com.kotuko.usermanagementsystem.config.KotukoException;
import com.kotuko.usermanagementsystem.user.dto.request.UserRequest;
import com.kotuko.usermanagementsystem.user.dto.request.UserSignInRequest;
import com.kotuko.usermanagementsystem.user.dto.response.SignInResponse;
import com.kotuko.usermanagementsystem.user.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request) throws Exception;

    UserResponse getUserByUsername(String username) throws KotukoException;

    List<UserResponse> getUsersByFirstName(String firstName,int page,int size) throws KotukoException;

    List<UserResponse> getUsersByLastName(String lastName,int page,int size) throws KotukoException;

    List<UserResponse> getUsersByEmail(String email,int page,int size) throws KotukoException;

    UserResponse updateUser(String id, UserRequest request) throws KotukoException;

    String deleteUser(String id) throws KotukoException;

    SignInResponse signIn(UserSignInRequest request);
}
