package com.kotuko.usermanagementsystem.user.mapper;

import com.kotuko.usermanagementsystem.user.dto.request.UserRequest;
import com.kotuko.usermanagementsystem.user.dto.response.UserResponse;
import com.kotuko.usermanagementsystem.user.entity.User;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "jsr330", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, imports = Collectors.class)
public interface IUserMapper {

    User toEntity(UserRequest request);

    @Mapping(target = "dob", source = "dateOfBirth")
    UserResponse fromEntityToUserResponse(User user);

    void toUpdateEntity(UserRequest request, @MappingTarget User user);

    List<UserResponse> toUserResponseList(List<User> users);
}
