package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDTO;
import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.entity.SuspiciousCardTransfer;
import com.bank.antifraud.service.SuspiciousCardTransferService;
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


@WebMvcTest(SuspiciousCardTransferController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = SuspiciousCardTransferController.class)
class SuspiciousCardTransferControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SuspiciousCardTransferService suspiciousCardTransferService;
    private SuspiciousCardTransferDTO suspiciousCardTransferDTO;
    private SuspiciousCardTransfer suspiciousCardTransfer;
    private List<SuspiciousCardTransfer> suspiciousCardTransferList = new ArrayList<>();
    private List<SuspiciousCardTransferDTO> suspiciousCardTransferDTOList = new ArrayList<>();
    private Long id;

    @BeforeEach
    void setUp() {
        id = 1L;
        suspiciousCardTransfer = new SuspiciousCardTransfer(1L, 1L,
                true, true, "string", "string");
        suspiciousCardTransferDTO = new SuspiciousCardTransferDTO(1L, 1L,
                true, true, "string", "string");
        suspiciousCardTransferList.add(suspiciousCardTransfer);
        suspiciousCardTransferDTOList.add(suspiciousCardTransferDTO);
        when(suspiciousCardTransferService.add(any())).thenReturn(suspiciousCardTransferDTO);
        when(suspiciousCardTransferService.getById(1L)).thenReturn(suspiciousCardTransferDTO);
        when(suspiciousCardTransferService.update(eq(1L), any(SuspiciousCardTransferDTO.class))).thenReturn(suspiciousCardTransferDTO);
        when(suspiciousCardTransferService.getAll()).thenReturn(suspiciousCardTransferDTOList);
    }

    @Test
    public void saveTest() throws Exception {

        mockMvc.perform(post("/api/suspicious-card-transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().findAndRegisterModules().writeValueAsString(suspiciousCardTransferDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(suspiciousCardTransferDTO.getId()));

        verify(suspiciousCardTransferService).add(any());
    }

    @Test
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/api/suspicious-card-transfer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(suspiciousCardTransferDTO.getId()));
        verify(suspiciousCardTransferService).getById(any());
    }

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/api/suspicious-card-transfer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(suspiciousCardTransferDTOList.size())));

        verify(suspiciousCardTransferService).getAll();
    }

    @Test
    public void deleteTest() throws Exception {

        mockMvc.perform(delete("/api/suspicious-card-transfer/" + id))
                .andExpect(status().isOk());

        verify(suspiciousCardTransferService).delete(id);
    }

    @Test
    public void updateTest() throws Exception {

        SuspiciousAccountTransfersDTO dto = new SuspiciousAccountTransfersDTO(1L, 1L,
                true, true, "string", "string");
        mockMvc.perform(put("/api/suspicious-card-transfer/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().findAndRegisterModules().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(suspiciousCardTransferDTO.getId()));

        verify(suspiciousCardTransferService).update(eq(id), any(SuspiciousCardTransferDTO.class));
    }

    @Test
    public void invalidJsonSaveTest() throws Exception {
        String invalidJson = "invalid Json";
        mockMvc.perform(post("/api/suspicious-card-transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
        verify(suspiciousCardTransferService, times(0)).add(any(SuspiciousCardTransferDTO.class));

    }

    @Test
    public void invalidJsonUpdateTest() throws Exception {
        String invalidJson = "";

        mockMvc.perform(put("/api/suspicious-card-transfer/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
        verify(suspiciousCardTransferService, times(0)).update(eq(id), any(SuspiciousCardTransferDTO.class));
    }

}