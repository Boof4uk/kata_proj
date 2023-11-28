package com.bank.authorization.controller;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.service.AuditService;
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

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AuditController.class)
@ContextConfiguration(classes = AuditController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuditControllerTest {

    @MockBean
    AuditService auditService;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;

    private AuditDto auditDto1;

    private AuditDto auditDto2;

    List<AuditDto> expectedAuditDtoList;

    @BeforeEach
    void setUp() {
        auditDto1 = new AuditDto(1L,
                "entityType",
                "operationType",
                "createdBy",
                "modifiedBy",
                LocalDateTime.of(2023, Month.NOVEMBER, 26, 22, 25),
                LocalDateTime.of(2023, Month.NOVEMBER, 26, 22, 25),
                "newEntityJson",
                "entityJson");
        auditDto2 = new AuditDto(2L,
                "entityType",
                "operationType",
                "createdBy",
                "modifiedBy",
                LocalDateTime.of(2023, Month.NOVEMBER, 26, 22, 25),
                LocalDateTime.of(2023, Month.NOVEMBER, 26, 22, 25),
                "newEntityJson",
                "entityJson");


        expectedAuditDtoList = List.of(auditDto1, auditDto2);

    }

    @Test
    public void testShowAll() throws Exception {

        when(auditService.getAll()).thenReturn(expectedAuditDtoList);

        mockMvc.perform(get("/api/auth-audit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void create() throws Exception {
        String auditJson = objectMapper.writeValueAsString(auditDto1);
        mockMvc.perform(post("/api/auth-audit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(auditJson))
                .andExpect(status().isCreated());
        verify(auditService).add(any(AuditDto.class));
    }


    @Test
    void getById() throws Exception {
        Long id = 1L;
        when(auditService.getById(id)).thenReturn(expectedAuditDtoList.get(0));
        when(auditService.getById(id)).thenReturn(auditDto1);

        mockMvc.perform(get("/api/auth-audit/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void update() throws Exception {
        String auditJson = objectMapper.writeValueAsString(auditDto1);
        mockMvc.perform(put("/api/auth-audit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(auditJson))
                .andExpect(status().isOk());
        verify(auditService).update(any(AuditDto.class));
    }

    @Test
    void delete() throws Exception {
        Long id = 1L;
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/auth-audit/" + id))
                .andExpect(status().isNoContent());
        verify(auditService).deleteById(id);
    }
}