package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDTO;
import com.bank.antifraud.entity.SuspiciousAccountTransfers;
import com.bank.antifraud.service.SuspiciousAccountTransfersService;
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


@WebMvcTest(SuspiciousAccountTransfersController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = SuspiciousAccountTransfersController.class)
class SuspiciousAccountTransfersControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SuspiciousAccountTransfersService suspiciousAccountTransfersService;
    private SuspiciousAccountTransfersDTO suspiciousAccountTransfersDTO;
    private SuspiciousAccountTransfers suspiciousAccountTransfers;
    private List<SuspiciousAccountTransfers> suspiciousAccountTransfersList = new ArrayList<>();
    private List<SuspiciousAccountTransfersDTO> suspiciousAccountTransfersDTOList = new ArrayList<>();
    private Long id;

    @BeforeEach
    void setUp() {
        id = 1L;
        suspiciousAccountTransfers = new SuspiciousAccountTransfers(1L, 1L,
                true, true, "string", "string");
        suspiciousAccountTransfersDTO = new SuspiciousAccountTransfersDTO(1L, 1L,
                true, true, "string", "string");
        suspiciousAccountTransfersList.add(suspiciousAccountTransfers);
        suspiciousAccountTransfersDTOList.add(suspiciousAccountTransfersDTO);
        when(suspiciousAccountTransfersService.add(any())).thenReturn(suspiciousAccountTransfersDTO);
        when(suspiciousAccountTransfersService.getById(1L)).thenReturn(suspiciousAccountTransfersDTO);
        when(suspiciousAccountTransfersService.update(eq(1L), any(SuspiciousAccountTransfersDTO.class))).thenReturn(suspiciousAccountTransfersDTO);
        when(suspiciousAccountTransfersService.getAll()).thenReturn(suspiciousAccountTransfersDTOList);
    }

    @Test
    public void saveTest() throws Exception {

        mockMvc.perform(post("/api/suspicious-account-transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().findAndRegisterModules().writeValueAsString(suspiciousAccountTransfersDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(suspiciousAccountTransfersDTO.getId()));

        verify(suspiciousAccountTransfersService).add(any());
    }

    @Test
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/api/suspicious-account-transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(suspiciousAccountTransfersDTO.getId()));
        verify(suspiciousAccountTransfersService).getById(any());
    }

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/api/suspicious-account-transfers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(suspiciousAccountTransfersDTOList.size())));

        verify(suspiciousAccountTransfersService).getAll();
    }

    @Test
    public void deleteTest() throws Exception {

        mockMvc.perform(delete("/api/suspicious-account-transfers/" + id))
                .andExpect(status().isOk());

        verify(suspiciousAccountTransfersService).delete(id);
    }

    @Test
    public void updateTest() throws Exception {

        SuspiciousAccountTransfersDTO dto = new SuspiciousAccountTransfersDTO(1L, 1L,
                true, true, "string", "string");
        mockMvc.perform(put("/api/suspicious-account-transfers/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().findAndRegisterModules().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(suspiciousAccountTransfersDTO.getId()));

        verify(suspiciousAccountTransfersService).update(eq(id), any(SuspiciousAccountTransfersDTO.class));
    }

    @Test
    public void invalidJsonSaveTest() throws Exception {
        String invalidJson = "invalid Json";
        mockMvc.perform(post("/api/suspicious-account-transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
        verify(suspiciousAccountTransfersService, times(0)).add(any(SuspiciousAccountTransfersDTO.class));

    }

    @Test
    public void invalidJsonUpdateTest() throws Exception {
        String invalidJson = "";

        mockMvc.perform(put("/api/suspicious-account-transfers/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
        verify(suspiciousAccountTransfersService, times(0)).update(eq(id), any(SuspiciousAccountTransfersDTO.class));
    }

}