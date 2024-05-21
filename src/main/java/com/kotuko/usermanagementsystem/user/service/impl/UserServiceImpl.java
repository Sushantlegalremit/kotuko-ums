package com.kotuko.usermanagementsystem.user.service.impl;

import com.kotuko.usermanagementsystem.config.KotukoException;
import com.kotuko.usermanagementsystem.user.dal.IUserDAL;
import com.kotuko.usermanagementsystem.user.dto.request.UserRequest;
import com.kotuko.usermanagementsystem.user.dto.response.UserResponse;
import com.kotuko.usermanagementsystem.user.entity.User;
import com.kotuko.usermanagementsystem.user.mapper.IUserMapper;
import com.kotuko.usermanagementsystem.user.service.UserService;
import com.kotuko.usermanagementsystem.user.validator.UserValidation;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final IUserDAL iUserDAL;
    private final IUserMapper iUserMapper;
    private final UserValidation userValidation;

    public UserServiceImpl(IUserDAL iUserDAL, IUserMapper iUserMapper, UserValidation userValidation) {
        this.iUserDAL = iUserDAL;
        this.iUserMapper = iUserMapper;
        this.userValidation = userValidation;
    }

    @Override
    public UserResponse createUser(UserRequest request) throws Exception {
        userValidation.dateOfBirthValidation(request.getDateOfBirth());
        userValidation.emailValidation(request.getEmail());
        userValidation.usernameValidation(request.getUsername());
        return iUserMapper.fromEntityToUserResponse(iUserDAL.save(iUserMapper.toEntity(request)));
    }

    @Override
    public UserResponse getUserByUsername(String username) throws KotukoException {
        User user = iUserDAL.findByUsernameIgnoreCase(username);
        if (user == null) {
            throw new KotukoException(HttpStatus.NOT_FOUND, "User not found with username: " + username);
        }
        return iUserMapper.fromEntityToUserResponse(user);
    }

    @Override
    public List<UserResponse> getUsersByFirstName(String firstName,int page,int size) throws KotukoException {
        User user = iUserDAL.findByFirstNameIgnoreCase(firstName);
        if (user == null) {
            throw new KotukoException(HttpStatus.NOT_FOUND, "User not found with firstname: " + firstName);
        }
        return iUserMapper.toUserResponseList(iUserDAL.findAllByFirstName(firstName,PageRequest.of(page,size)).stream().toList());
    }

    @Override
    public List<UserResponse> getUsersByLastName(String lastName,int page,int size) throws KotukoException {
        User user = iUserDAL.findByLastNameIgnoreCase(lastName);
        if (user == null) {
            throw new KotukoException(HttpStatus.NOT_FOUND, "User not found with lastname: " + lastName);
        }
        return iUserMapper.toUserResponseList(iUserDAL.findByLastName(lastName, PageRequest.of(page,size)).stream().toList());
    }

    @Override
    public List<UserResponse> getUsersByEmail(String email,int page,int size) throws KotukoException {
        User user = iUserDAL.findByEmailIgnoreCase(email);
        if (user == null) {
            throw new KotukoException(HttpStatus.NOT_FOUND, "User not found with email: " + email);
        }
        return iUserMapper.toUserResponseList(iUserDAL.findAllEmails(email,PageRequest.of(page,size)).stream().toList());
    }

    @Override
    public UserResponse updateUser(String id, UserRequest request) throws KotukoException {
        Optional<User> user = Optional.ofNullable(iUserDAL.findById(id).orElseThrow(() -> new KotukoException(HttpStatus.NOT_FOUND, "User does not exist")));

        if (user.isPresent()) {
            iUserMapper.toUpdateEntity(request, user.get());
            if (request.getDateOfBirth() != null)
                userValidation.dateOfBirthValidation(request.getDateOfBirth());
            if (request.getEmail() != null)
                userValidation.emailValidation(request.getEmail());
            if (request.getUsername() != null)
                userValidation.usernameValidation(request.getUsername());
            return iUserMapper.fromEntityToUserResponse(iUserDAL.save(user.get()));
        }
        return new UserResponse();
    }

    @Override
    public String deleteUser(String id) throws KotukoException {
        Optional<User> user = iUserDAL.findById(id);
        if (user.isEmpty())
            throw new KotukoException(HttpStatus.NOT_FOUND, "User doesnt exist");
        user.ifPresent(iUserDAL::delete);
        return "Deleted user with id: " + id;
    }
}
