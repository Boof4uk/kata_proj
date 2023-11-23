package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.service.AuditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import javax.persistence.EntityNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
@WebMvcTest(AuditController.class)
class AuditControllerTest {

    @MockBean
    AuditService auditService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    private List<AuditDTO> getAudits(){
        AuditDTO auditDTO1 = new AuditDTO(1L,
                "тип сущности",
                "тип операции",
                "кто создал",
                "кто изменил",
                "2023-11-14 20:36:36",
                "2023-11-14 20:36:36",
                "json, заполняется при изменении",
                "json, заполняется при изменении и сохранении");
        AuditDTO auditDTO2 = new AuditDTO(2L,
                "тип сущности",
                "тип операции",
                "кто создал",
                "кто изменил",
                "2023-11-14T20:36:36",
                "2023-11-14T20:36:36",
                "json, заполняется при изменении",
                "json, заполняется при изменении и сохранении");;
        return List.of(auditDTO1,auditDTO2);
    }

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void getAllTest() throws Exception {
        Mockito.when(auditService.getAll()).thenReturn(getAudits());

        mockMvc.perform(get("/audit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void getAuditTest() throws Exception {
        Mockito.when(auditService.find(1L)).thenReturn(getAudits().get(0));

        mockMvc.perform(get("/audit/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
    @Test
    public void shouldHandleResourceNotFoundExceptionTest() throws Exception {
        Long nonExistentId = 5L;
        Mockito.when(auditService.find(nonExistentId)).thenThrow(new EntityNotFoundException("Audit not found"));

        mockMvc.perform(get("/audit/" + nonExistentId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Audit not found"));
    }

    @Test
    public void createAuditTest() throws Exception {
        String auditJson = objectMapper.writeValueAsString(getAudits().get(1));
        mockMvc.perform(post("/audit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(auditJson))
                .andExpect(status().isOk());
        verify(auditService).save(any(AuditDTO.class));
    }

    @Test
    public void notValidCreateAuditTest() throws Exception {
        AuditDTO badAuditDto = getAudits().get(1);
        badAuditDto.setCreatedAt(null);
        String auditJson = objectMapper.writeValueAsString(badAuditDto);

        mockMvc.perform(post("/audit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(auditJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void editAuditTest() throws Exception {
        String auditJson = objectMapper.writeValueAsString(getAudits().get(1));
        mockMvc.perform(put("/audit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(auditJson))
                .andExpect(status().isOk());
        verify(auditService).update(any(AuditDTO.class));
    }

    @Test
    public void notValidEditAuditTest() throws Exception {
        AuditDTO badAuditDto = getAudits().get(1);
        badAuditDto.setCreatedAt(null);
        String auditJson = objectMapper.writeValueAsString(badAuditDto);

        mockMvc.perform(put("/audit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(auditJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteAuditTest() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/audit/"+ id))
                .andExpect(status().isOk());
        verify(auditService).delete(id);
    }
}