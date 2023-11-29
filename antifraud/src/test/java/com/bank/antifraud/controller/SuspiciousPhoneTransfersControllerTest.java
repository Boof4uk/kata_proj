package com.bank.antifraud.controller;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDTO;
import com.bank.antifraud.dto.SuspiciousPhoneTransfersDTO;
import com.bank.antifraud.entity.SuspiciousPhoneTransfers;
import com.bank.antifraud.service.SuspiciousPhoneTransfersService;
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

@WebMvcTest(SuspiciousPhoneTransfersController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = SuspiciousPhoneTransfersController.class)
class SuspiciousPhoneTransfersControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SuspiciousPhoneTransfersService suspiciousPhoneTransfersService;
    private SuspiciousPhoneTransfersDTO suspiciousPhoneTransfersDTO;
    private SuspiciousPhoneTransfers suspiciousPhoneTransfers;
    private List<SuspiciousPhoneTransfers> suspiciousPhoneTransfersList = new ArrayList<>();
    private List<SuspiciousPhoneTransfersDTO> suspiciousPhoneTransfersDTOList = new ArrayList<>();
    private Long id;

    @BeforeEach
    void setUp() {
        id = 1L;
        suspiciousPhoneTransfers = new SuspiciousPhoneTransfers(1L, 1L,
                true, true, "string", "string");
        suspiciousPhoneTransfersDTO = new SuspiciousPhoneTransfersDTO(1L, 1L,
                true, true, "string", "string");
        suspiciousPhoneTransfersList.add(suspiciousPhoneTransfers);
        suspiciousPhoneTransfersDTOList.add(suspiciousPhoneTransfersDTO);
        when(suspiciousPhoneTransfersService.add(any())).thenReturn(suspiciousPhoneTransfersDTO);
        when(suspiciousPhoneTransfersService.getById(1L)).thenReturn(suspiciousPhoneTransfersDTO);
        when(suspiciousPhoneTransfersService.update(eq(1L), any(SuspiciousPhoneTransfersDTO.class))).thenReturn(suspiciousPhoneTransfersDTO);
        when(suspiciousPhoneTransfersService.getAll()).thenReturn(suspiciousPhoneTransfersDTOList);
    }

    @Test
    public void saveTest() throws Exception {

        mockMvc.perform(post("/api/suspicious-phone-transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().findAndRegisterModules().writeValueAsString(suspiciousPhoneTransfersDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(suspiciousPhoneTransfersDTO.getId()));

        verify(suspiciousPhoneTransfersService).add(any());
    }

    @Test
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("/api/suspicious-phone-transfers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(suspiciousPhoneTransfersDTO.getId()));
        verify(suspiciousPhoneTransfersService).getById(any());
    }

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/api/suspicious-phone-transfers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(suspiciousPhoneTransfersDTOList.size())));

        verify(suspiciousPhoneTransfersService).getAll();
    }

    @Test
    public void deleteTest() throws Exception {

        mockMvc.perform(delete("/api/suspicious-phone-transfers/" + id))
                .andExpect(status().isOk());

        verify(suspiciousPhoneTransfersService).delete(id);
    }

    @Test
    public void updateTest() throws Exception {

        SuspiciousAccountTransfersDTO dto = new SuspiciousAccountTransfersDTO(1L, 1L,
                true, true, "string", "string");
        mockMvc.perform(put("/api/suspicious-phone-transfers/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().findAndRegisterModules().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(suspiciousPhoneTransfersDTO.getId()));

        verify(suspiciousPhoneTransfersService).update(eq(id), any(SuspiciousPhoneTransfersDTO.class));
    }

    @Test
    public void invalidJsonSaveTest() throws Exception {
        String invalidJson = "invalid Json";
        mockMvc.perform(post("/api/suspicious-phone-transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
        verify(suspiciousPhoneTransfersService, times(0)).add(any(SuspiciousPhoneTransfersDTO.class));

    }

    @Test
    public void invalidJsonUpdateTest() throws Exception {
        String invalidJson = "";

        mockMvc.perform(put("/api/suspicious-phone-transfers/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
        verify(suspiciousPhoneTransfersService, times(0)).update(eq(id), any(SuspiciousPhoneTransfersDTO.class));
    }

}