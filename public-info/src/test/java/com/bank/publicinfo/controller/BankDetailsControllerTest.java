package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.service.BankDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.pro.packaged.B;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankDetailsController.class)
class BankDetailsControllerTest {

    @MockBean
    BankDetailsService bankDetailsService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private BankDetailsDTO bankDetailsDTO1;
    private BankDetailsDTO bankDetailsDTO2;
    @BeforeEach
    public void setUp(){
        bankDetailsDTO1 = new BankDetailsDTO(1L,
                100L,
                200L,
                300L,
                400,
                "city",
                "company",
                "name");
        bankDetailsDTO2 = new BankDetailsDTO(2L,
                200L,
                222L,
                333L,
                444,
                "city2",
                "company2",
                "name2");
    }

    @Test
    public void getAllTest() throws Exception {
        Mockito.when(bankDetailsService.getAll()).thenReturn(List.of(bankDetailsDTO1,bankDetailsDTO2));

        mockMvc.perform(get("/bankdetails"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    public void getBankDetailsTest() throws Exception {
        Mockito.when(bankDetailsService.find(1L)).thenReturn(bankDetailsDTO1);

        mockMvc.perform(get("/bankdetails/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void shouldHandleResourceNotFoundExceptionTest() throws Exception {
        Long nonExistentId = 5L;
        Mockito.when(bankDetailsService.find(nonExistentId)).thenThrow(new EntityNotFoundException("BankDetails not found"));

        mockMvc.perform(get("/bankdetails/" + nonExistentId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("BankDetails not found"));
    }

    @Test
    public void createBankDetailsTest() throws Exception {
        String auditJson = objectMapper.writeValueAsString(bankDetailsDTO1);
        mockMvc.perform(post("/bankdetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(auditJson))
                .andExpect(status().isOk());
        verify(bankDetailsService).save(any(BankDetailsDTO.class));
    }

    @Test
    public void notValidCreateBankDetailsTest() throws Exception {
        BankDetailsDTO badBankDetailsDto = bankDetailsDTO1;
        badBankDetailsDto.setBik(null);
        String auditJson = objectMapper.writeValueAsString(badBankDetailsDto);

        mockMvc.perform(post("/bankdetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(auditJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void editBankDetailsTest() throws Exception {
        String bankDetailsJson = objectMapper.writeValueAsString(bankDetailsDTO1);
        mockMvc.perform(put("/bankdetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bankDetailsJson))
                .andExpect(status().isOk());
        verify(bankDetailsService).update(any(BankDetailsDTO.class));
    }

    @Test
    public void notValidEditAuditTest() throws Exception {
        BankDetailsDTO bankDetailsDTO = bankDetailsDTO1;
        bankDetailsDTO.setBik(null);
        String BankDetailsJson = objectMapper.writeValueAsString(bankDetailsDTO);

        mockMvc.perform(put("/bankdetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BankDetailsJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteBankDetailsTest() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/bankdetails/"+ id))
                .andExpect(status().isOk());
        verify(bankDetailsService).delete(id);
    }
}