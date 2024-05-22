package com.kotuko.usermanagementsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kotuko.usermanagementsystem.helper.UserHelper;
import com.kotuko.usermanagementsystem.user.dto.request.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void createUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/users")
                        .content(new ObjectMapper().writeValueAsString(UserHelper.createUser()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void getUserByUsernameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users/testUsername1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void getUserByFirstnameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users/first-name/testFirstName")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void getUserByLastnameTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users/last-name/testLastName")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void getUserByEmailTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users/email/admisn@mail.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void updateUserByUserId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/users/9e6571b4-de15-4a30-969f-f57d6c0dce14")
                        .content(new ObjectMapper().writeValueAsString(new UserRequest(null, null, null, "usernameupdated", null, null, null)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    public void deleteUserByUserId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/users/10b717c8-4b73-41c9-9669-1bc529b368b3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
