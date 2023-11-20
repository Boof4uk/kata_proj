package com.bank.profile.controller;

import com.bank.profile.dto.request.AccountDetailsRequestDto;
import com.bank.profile.dto.response.AccountDetailsResponseDto;
import com.bank.profile.dto.response.ProfileResponseDto;
import com.bank.profile.entity.Profile;
import com.bank.profile.exeption.ResourceNotFoundException;
import com.bank.profile.service.AccountDetailsService;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountDetailsController.class)
class AccountDetailsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountDetailsService accountDetailsService;
    private Profile profile;
    private ProfileResponseDto profileResponseDto;
    private AccountDetailsRequestDto accountDetailsRequestDto;
    private AccountDetailsResponseDto accountDetailsResponseDto;
    private List<AccountDetailsResponseDto> accountDetailsResponseDtoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        profile = mock(Profile.class);
        profileResponseDto = mock(ProfileResponseDto.class);
        accountDetailsRequestDto = new AccountDetailsRequestDto(1L, 1L);
        accountDetailsResponseDto = new AccountDetailsResponseDto(1l, 1L, profileResponseDto);
        accountDetailsResponseDtoList.add(accountDetailsResponseDto);
        when(accountDetailsService.create(any())).thenReturn(accountDetailsResponseDto);
        when(accountDetailsService.getByProfileId(1L)).thenReturn(accountDetailsResponseDtoList);
        when(accountDetailsService.getById(1l)).thenReturn(accountDetailsResponseDto);
        when(accountDetailsService.update(any(Long.class), any(AccountDetailsRequestDto.class))).thenReturn(accountDetailsResponseDto);
    }


    @Test
    void createAccountDetails() throws Exception {

        mockMvc.perform(post("/api/account-details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(accountDetailsRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(accountDetailsResponseDto.id()));

        verify(accountDetailsService).create(any());
    }

    @Test
    void update() throws Exception {
        Long id = 1L;
        AccountDetailsRequestDto requestDto = new AccountDetailsRequestDto(2L,1L);

        mockMvc.perform(put("/api/account-details/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(accountDetailsResponseDto.id()));

        verify(accountDetailsService).update(eq(id), any(AccountDetailsRequestDto.class));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/account-details/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(accountDetailsResponseDto.id()));
        verify(accountDetailsService).getById(any());
    }
    @Test
    void shouldHandleResourceNotFoundException() throws Exception {
        Long nonExistentId = 999L;
        when(accountDetailsService.getById(nonExistentId))
                .thenThrow(new ResourceNotFoundException("AccountDetails", "id", nonExistentId));

        mockMvc.perform(get("/api/account-details/" + nonExistentId))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.httpStatus").value("CONFLICT"))
                .andExpect(jsonPath("$.message").value("AccountDetails not found with id : " + nonExistentId));
    }

    @Test
    void getByProfileId() throws Exception {
        mockMvc.perform(get("/api/account-details/all/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(accountDetailsResponseDtoList.size())));

        verify(accountDetailsService).getByProfileId(any());
    }

    @Test
    void deleteAccountDetails() throws Exception {
        Long idToDelete = 1L;

        mockMvc.perform(delete("/api/account-details/" + idToDelete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(accountDetailsService).delete(idToDelete);
    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}