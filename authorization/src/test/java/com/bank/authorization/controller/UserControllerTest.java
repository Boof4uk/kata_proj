package com.bank.authorization.controller;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.dto.UserDto;
import com.bank.authorization.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    private UserDto userAdmin;

    private UserDto userUser;

    List<UserDto> expectedUserDtoList;

    @BeforeEach
    void setUpTest() {
        userAdmin = new UserDto(1L,
                "ADMIN",
                1L,
                "password1");

        userUser = new UserDto(2L,
                "USER",
                2L,
                "password2");

        expectedUserDtoList = List.of(userAdmin, userUser);
    }

    @Test
    void getAllTest() throws Exception {
        when(userService.getAll()).thenReturn(expectedUserDtoList);

        mockMvc.perform(get("/api/auth-user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void createUserTest() throws Exception {
        String auditJson = objectMapper.writeValueAsString(userAdmin);
        mockMvc.perform(post("/api/auth-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(auditJson))
                .andExpect(status().isCreated());
        verify(userService).add(any(UserDto.class));
    }

    @Test
    void updateUserTest() throws Exception {
        String auditJson = objectMapper.writeValueAsString(userAdmin);
        mockMvc.perform(put("/api/auth-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(auditJson))
                .andExpect(status().isOk());
        verify(userService).update(any(UserDto.class));
    }

    @Test
    void getUserByIdTest() throws Exception {
        Long id = 1L;
        when(userService.getById(id)).thenReturn(expectedUserDtoList.get(0));
        when(userService.getById(id)).thenReturn(userAdmin);

        mockMvc.perform(get("/api/auth-user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void deleteUserByIdTest() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/api/auth-user/" + id))
                .andExpect(status().isNoContent());
        verify(userService).deleteById(id);
    }
}