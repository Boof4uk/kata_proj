package com.bank.profile.controller;

import com.bank.profile.dto.request.AuditRequestDto;
import com.bank.profile.dto.response.AuditResponseDto;
import com.bank.profile.entity.Audit;
import com.bank.profile.exception.ResourceNotFoundException;
import com.bank.profile.service.AuditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuditController.class)
class AuditControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuditService auditService;
    private Audit audit;
    private AuditResponseDto auditResponseDto;
    private AuditRequestDto auditRequestDto;
    List<Audit> auditList = new ArrayList<>();
    List<AuditResponseDto> auditResponseDtoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        auditRequestDto = new AuditRequestDto("test",
                "test", "test", "test",
                LocalDateTime.of(2022, 2, 22, 22, 22, 22),
                LocalDateTime.of(2023, 3, 23, 23, 23, 23),
                "test", "test");

        auditResponseDto = new AuditResponseDto(1L, "test",
                "test", "test", "test",
                "2022-2-22 22:22:22", "2023-3-23:23:23",
                "test", "test");

        audit = new Audit(1L, "test",
                "test", "test", "test",
                LocalDateTime.of(2022, 2, 22, 22, 22, 22),
                LocalDateTime.of(2023, 3, 23, 23, 23, 23),
                "test", "test");
        auditResponseDtoList = List.of(auditResponseDto);
        auditList = List.of(audit);
        when(auditService.create(any())).thenReturn(auditResponseDto);
        when(auditService.getById(1L)).thenReturn(auditResponseDto);
        when(auditService.update(eq(1L), any(AuditRequestDto.class))).thenReturn(auditResponseDto);
        when(auditService.getAll()).thenReturn(auditResponseDtoList);
    }

    @Test
    void create() throws Exception {

        mockMvc.perform(post("/api/audit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(auditRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(auditResponseDto.id()));

        verify(auditService).create(any());
    }

    @Test
    void update() throws Exception {
        Long id = 1L;
        AuditRequestDto requestDto = new AuditRequestDto("test",
                "newtest", "newtest", "newtest",
                LocalDateTime.of(2022, 2, 22, 22, 22, 22),
                LocalDateTime.of(2023, 3, 23, 23, 23, 23),
                "newtest", "newtest");
        mockMvc.perform(put("/api/audit/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(auditResponseDto.id()));

        verify(auditService).update(eq(id), any(AuditRequestDto.class));

    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/audit/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(auditResponseDto.id()));
        verify(auditService).getById(any());
    }
    @Test
    void shouldHandleResourceNotFoundException() throws Exception {
        Long nonExistentId = 999L;
        when(auditService.getById(nonExistentId))
                .thenThrow(new ResourceNotFoundException("Audit", "id", nonExistentId));

        mockMvc.perform(get("/api/audit/" + nonExistentId))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.httpStatus").value("CONFLICT"))
                .andExpect(jsonPath("$.message").value("Audit not found with id : " + nonExistentId));
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/audit/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(auditResponseDtoList.size())));

        verify(auditService).getAll();
    }

    @Test
    void deleteAudit() throws Exception {
        Long idToDelete = 1L;

        mockMvc.perform(delete("/api/audit/" + idToDelete)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(auditService).delete(idToDelete);
    }


    private String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // чтобы выводить даты в ISO формате
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}