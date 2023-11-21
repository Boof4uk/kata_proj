package com.bank.profile.controller;

import com.bank.profile.dto.request.RegistrationRequestDto;
import com.bank.profile.dto.response.RegistrationResponseDto;
import com.bank.profile.entity.Registration;
import com.bank.profile.exception.ResourceNotFoundException;
import com.bank.profile.service.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RegistrationService registrationService;
    private Registration registration;
    private RegistrationResponseDto registrationResponseDto;
    private RegistrationRequestDto registrationRequestDto;
    private List<Registration> registrationList = new ArrayList<>();
    private List<RegistrationResponseDto> registrationResponseDtoList = new ArrayList<>();


    @BeforeEach
    void setUp() {

        registration =new Registration(
                1L,"Russia","Moscow Region","Moscow","Central","Moscow","Tverskaya","10","A","101",123456L);

        registrationRequestDto = new RegistrationRequestDto(
                "Russia","Moscow Region","Moscow","Central","Moscow","Tverskaya","10","A","101",123456L);
        registrationResponseDto = new RegistrationResponseDto(
                1L,"Russia","Moscow Region","Moscow","Central","Moscow","Tverskaya","10","A","101",123456L);
        registrationList.add(registration);
        registrationResponseDtoList.add(registrationResponseDto);
        when(registrationService.create(any())).thenReturn(registrationResponseDto);
        when(registrationService.getById(1L)).thenReturn(registrationResponseDto);
        when(registrationService.update(eq(1L), any(RegistrationRequestDto.class))).thenReturn(registrationResponseDto);
        when(registrationService.getAll()).thenReturn(registrationResponseDtoList);
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/api/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registrationRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(registrationResponseDto.id()));

        verify(registrationService).create(any());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/registration/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(registrationResponseDto.id()));
        verify(registrationService).getById(any());
    }

    @Test
    void update() throws Exception {
        Long id = 1L;
        RegistrationRequestDto requestDto = new RegistrationRequestDto("Russia_New","Moscow Region new","Moscow New","Central new","Moscow","Tverskaya","10","A","101",123456L);
        mockMvc.perform(put("/api/registration/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(registrationResponseDto.id()));

        verify(registrationService).update(eq(id), any(RegistrationRequestDto.class));
    }
    @Test
    void shouldHandleResourceNotFoundException() throws Exception {
        Long nonExistentId = 999L;
        when(registrationService.getById(nonExistentId))
                .thenThrow(new ResourceNotFoundException("Registration", "id", nonExistentId));

        mockMvc.perform(get("/api/registration/" + nonExistentId))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.httpStatus").value("CONFLICT"))
                .andExpect(jsonPath("$.message").value("Registration not found with id : " + nonExistentId));
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/registration/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(registrationResponseDtoList.size())));

        verify(registrationService).getAll();
    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}