package com.bank.antifraud.controller;

import com.bank.antifraud.dto.AntifraudAuditDTO;
import com.bank.antifraud.entity.AntifraudAudit;
import com.bank.antifraud.service.AntifraudAuditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AntifraudAuditController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = AntifraudAuditController.class)
class AntifraudAuditControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AntifraudAuditService antifraudAuditService;
    private AntifraudAuditDTO antifraudAuditDTO;
    private AntifraudAudit antifraudAudit;
    private List<AntifraudAudit> antifraudAuditList = new ArrayList<>();
    private List<AntifraudAuditDTO> antifraudAuditDTOList = new ArrayList<>();
    private Long id;


    @BeforeEach
    void setUp() {
        id = 1L;
        antifraudAudit = new AntifraudAudit(1L, "string", "string",
                "string", "string", LocalDateTime.of(2014, Month.DECEMBER, 6, 12, 20),
                LocalDateTime.of(2019, Month.MAY, 23, 15, 33), "newJson", "Json");
        antifraudAuditDTO = new AntifraudAuditDTO(1L, "string", "string",
                "string", "string", LocalDateTime.of(2014, Month.DECEMBER, 6, 12, 20),
                LocalDateTime.of(2019, Month.MAY, 23, 15, 33), "newJson", "Json");
        antifraudAuditList.add(antifraudAudit);
        antifraudAuditDTOList.add(antifraudAuditDTO);
        when(antifraudAuditService.add(any())).thenReturn(antifraudAuditDTO);
        when(antifraudAuditService.getById(1L)).thenReturn(antifraudAuditDTO);
        when(antifraudAuditService.update(eq(1L), any(AntifraudAuditDTO.class))).thenReturn(antifraudAuditDTO);
        when(antifraudAuditService.getAll()).thenReturn(antifraudAuditDTOList);
    }


    @Test
    public void saveTest() throws Exception {

        mockMvc.perform(post("/api/antifraud-audit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().findAndRegisterModules().writeValueAsString(antifraudAuditDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(antifraudAuditDTO.getId()));

        verify(antifraudAuditService).add(any());
    }

    @Test
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/api/antifraud-audit/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(antifraudAuditDTO.getId()));
        verify(antifraudAuditService).getById(any());
    }

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/api/antifraud-audit")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(antifraudAuditDTOList.size())));

        verify(antifraudAuditService).getAll();
    }

    @Test
    public void deleteTest() throws Exception {

        mockMvc.perform(delete("/api/antifraud-audit/" + id))
                .andExpect(status().isNoContent());

        verify(antifraudAuditService).delete(id);
    }

    @Test
    public void updateTest() throws Exception {

        AntifraudAuditDTO dto = new AntifraudAuditDTO(1L, "string", "string",
                "string", "string", LocalDateTime.of(2014, Month.DECEMBER, 6, 12, 20),
                LocalDateTime.of(2019, Month.MAY, 23, 15, 33), "newJson", "Json");
        mockMvc.perform(put("/api/antifraud-audit/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().findAndRegisterModules().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(antifraudAuditDTO.getId()));

        verify(antifraudAuditService).update(eq(id), any(AntifraudAuditDTO.class));
    }

    @Test
    public void invalidJsonSaveTest() throws Exception {
        String invalidJson = "invalid Json";
        mockMvc.perform(post("/api/antifraud-audit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
        verify(antifraudAuditService, times(0)).add(any(AntifraudAuditDTO.class));

    }

    @Test
    public void invalidJsonUpdateTest() throws Exception {
        String invalidJson = "";

        mockMvc.perform(put("/api/antifraud-audit/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isOk());
        verify(antifraudAuditService, times(0)).update(eq(id), any(AntifraudAuditDTO.class));
    }


}