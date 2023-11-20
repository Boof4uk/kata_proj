package com.bank.profile.controller;

import com.bank.profile.dto.request.ProfileRequestDto;
import com.bank.profile.dto.response.ActualRegistrationResponseDto;
import com.bank.profile.dto.response.PassportResponseDto;
import com.bank.profile.dto.response.ProfileResponseDto;
import com.bank.profile.entity.AccountDetails;
import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Profile;
import com.bank.profile.exeption.ResourceNotFoundException;
import com.bank.profile.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfileController.class)
class ProfileControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProfileService profileService;
    private Profile profile;
    private ProfileRequestDto profileRequestDto;
    private ProfileResponseDto profileResponseDto;
    private List<Profile> profileList = new ArrayList<>();
    private List<ProfileResponseDto> profileResponseDtoList = new ArrayList<>();
    private Passport passport;
    private PassportResponseDto passportResponseDto;
    private ActualRegistration actualRegistration;
    private ActualRegistrationResponseDto actualRegistrationResponseDto;
    private Set<AccountDetails> accountDetailsSet = new HashSet<>();

    @BeforeEach
    void setUp() {
        passport = mock(Passport.class);
        actualRegistration = mock(ActualRegistration.class);
        actualRegistrationResponseDto = mock(ActualRegistrationResponseDto.class);
        passportResponseDto = mock(PassportResponseDto.class);
        AccountDetails accountDetails = mock(AccountDetails.class);
        accountDetailsSet.add(accountDetails);

        profileRequestDto = new ProfileRequestDto(1234567890L, "user@example.com", "John Doe", 123456789012L, 12345678901L, 1L, 2L);
        profileResponseDto = new ProfileResponseDto(1L, 1234567890L, "user@example.com", "John Doe", 123456789012L, 12345678901L, passportResponseDto, actualRegistrationResponseDto);
        profile = new Profile(1L, 1234567890L, "user@example.com", "John Doe", 123456789012L, 12345678901L, accountDetailsSet, passport, actualRegistration);
        profileList.add(profile);
        profileResponseDtoList.add(profileResponseDto);
        when(profileService.create(any())).thenReturn(profileResponseDto);
        when(profileService.getAll()).thenReturn(profileResponseDtoList);
        when(profileService.getById(1l)).thenReturn(profileResponseDto);
        when(profileService.update(any(Long.class), any(ProfileRequestDto.class))).thenReturn(profileResponseDto);

    }

    @Test
    void create()  throws Exception {
        mockMvc.perform(post("/api/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(profileRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(profileResponseDto.id()));

        verify(profileService).create(any(ProfileRequestDto.class));
    }

    @Test
    void byId()throws Exception {
        Long id = 1L;
        when(profileService.getById(id)).thenReturn(profileResponseDto);

        mockMvc.perform(get("/api/profile/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(profileResponseDto.id()));

        verify(profileService).getById(id);
    }

    @Test
    void update() throws Exception {
        Long id = 1L;

        mockMvc.perform(put("/api/profile/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(profileRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(profileResponseDto.id()));

        verify(profileService).update(eq(id), any(ProfileRequestDto.class));

    }

    @Test
    void deleteProfile() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/profile/" + id))
                .andExpect(status().isNoContent());

        verify(profileService).delete(id);
    }
    @Test
    void shouldHandleResourceNotFoundExceptionProfile() throws Exception {
        Long nonExistentId = 999L;
        when(profileService.getById(nonExistentId))
                .thenThrow(new ResourceNotFoundException("Profile", "id", nonExistentId));

        mockMvc.perform(get("/api/profile/" + nonExistentId))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.httpStatus").value("CONFLICT"))
                .andExpect(jsonPath("$.message").value("Profile not found with id : " + nonExistentId));
    }
    @Test
    void shouldHandleResourceNotFoundExceptionPassport() throws Exception {
        Long nonExistentPassportId = 999L; // ID паспорта, который не существует
        when(profileService.getById(nonExistentPassportId))
                .thenThrow(new ResourceNotFoundException("Passport", "id", nonExistentPassportId));

        mockMvc.perform(get("/api/profile/" + nonExistentPassportId))
                .andExpect(status().isConflict()) // Проверка на статус 409 Conflict
                .andExpect(jsonPath("$.httpStatus").value("CONFLICT"))
                .andExpect(jsonPath("$.message").value("Passport not found with id : " + nonExistentPassportId));

        verify(profileService).getById(nonExistentPassportId);
    }
    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/profile/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(profileResponseDtoList.size())));

        verify(profileService).getAll();
    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}