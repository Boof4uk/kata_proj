package com.bank.profile.handler;
import com.bank.profile.controller.RegistrationController;
import com.bank.profile.dto.request.RegistrationRequestDto;
import com.bank.profile.dto.response.RegistrationResponseDto;
import com.bank.profile.exception.ResourceNotFoundException;
import com.bank.profile.service.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
class ExceptionHandlerAdviceTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RegistrationService registrationService;

    @Test
    void whenCreateRegistrationWithInvalidData_thenBadRequest() throws Exception {
        String invalidJson = "invalid json string";

        mockMvc.perform(post("/api/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    void whenResourceNotFound_thenConflictResponse() throws Exception {
        RegistrationRequestDto requestDto = new RegistrationRequestDto(
                "Russia","Moscow Region","Moscow","Central",
                "Moscow","Tverskaya","10","A",
                "101",123456L);

        Long invalidId = 999L;
        when(registrationService.update(invalidId,requestDto)).thenThrow(
                new ResourceNotFoundException("Registration update", "RegistrationId", invalidId));


        mockMvc.perform(put("/api/registration/{id}", invalidId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andDo(print()) // Для вывода ответа
                .andExpect(jsonPath("$.httpStatus").value("CONFLICT"))
                .andExpect(jsonPath("$.message").value("Registration update not found with RegistrationId : " + invalidId));
    }
}