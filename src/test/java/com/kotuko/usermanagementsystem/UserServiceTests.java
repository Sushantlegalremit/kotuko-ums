package com.kotuko.usermanagementsystem;

import com.kotuko.usermanagementsystem.helper.UserHelper;
import com.kotuko.usermanagementsystem.user.dal.IUserDAL;
import com.kotuko.usermanagementsystem.user.dto.response.UserResponse;
import com.kotuko.usermanagementsystem.user.entity.User;
import com.kotuko.usermanagementsystem.user.mapper.IUserMapper;
import com.kotuko.usermanagementsystem.user.mapper.IUserMapperImpl;
import com.kotuko.usermanagementsystem.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Autowired
    private UserService userService;
    @Mock
    private IUserDAL iUserDAL;
    @Spy
    private IUserMapper iUserMapper;

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void createUserTest() throws Exception {
        UserResponse userResponse = userService.createUser(UserHelper.createUser());
        assertThat(userResponse.getId()).isNotEmpty();
        assertThat(userResponse).isNotNull();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void getUserByUsernameTest() throws Exception {
        UserResponse userResponse = userService.getUserByUsername("kotuko");
        assertThat(userResponse.getId()).isNotEmpty();
        assertThat(userResponse).isNotNull();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void getUsersByFirstNameTest() throws Exception {
        List<UserResponse> userResponse = userService.getUsersByFirstName("kotuko", 0, 10);
        assertThat(userResponse.size()).isGreaterThan(0);
        assertThat(userResponse).isNotNull();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void getUsersByLastNameTest() throws Exception {
        List<UserResponse> userResponse = userService.getUsersByLastName("kotuko", 0, 10);
        assertThat(userResponse.size()).isGreaterThan(0);
        assertThat(userResponse).isNotNull();
    }
    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void getUsersByEmailTest() throws Exception {
        List<UserResponse> userResponse = userService.getUsersByEmail("kotuko@gmail.com", 0, 10);
        assertThat(userResponse.size()).isGreaterThan(0);
        assertThat(userResponse).isNotNull();
    }
}
