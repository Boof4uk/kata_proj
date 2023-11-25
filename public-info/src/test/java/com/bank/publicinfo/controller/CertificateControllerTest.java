package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.service.CertificateService;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CertificateController.class)
class CertificateControllerTest {

    @MockBean
    CertificateService certificateService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private CertificateDTO certificateDTO1;
    private CertificateDTO certificateDTO2;

    @BeforeEach
    public void setUp(){
        certificateDTO1 = new CertificateDTO(1L,new byte[1],new BankDetailsDTO());
        certificateDTO2 = new CertificateDTO(2L,new byte[1],new BankDetailsDTO());
    }

    @Test
    public void getAllTest() throws Exception {
        Mockito.when(certificateService.getAll()).thenReturn(List.of(certificateDTO1,certificateDTO2));

        mockMvc.perform(get("/certificate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void getCertificateTest() throws Exception {
        Mockito.when(certificateService.find(1L)).thenReturn(certificateDTO1);

        mockMvc.perform(get("/certificate/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
    @Test
    public void shouldHandleResourceNotFoundExceptionTest() throws Exception {
        Long nonExistentId = 5L;
        Mockito.when(certificateService.find(nonExistentId)).thenThrow(new EntityNotFoundException("Certificate not found"));

        mockMvc.perform(get("/certificate/" + nonExistentId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Certificate not found"));
    }

    @Test
    public void createCertificateTest() throws Exception {
        String certificateJson = objectMapper.writeValueAsString(certificateDTO1);
        mockMvc.perform(post("/certificate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(certificateJson))
                .andExpect(status().isOk());
        verify(certificateService).save(any(CertificateDTO.class));
    }

    @Test
    public void notValidCreateCertificateTest() throws Exception {
        CertificateDTO badCertificateDto = certificateDTO1;
        badCertificateDto.setPhoto(null);
        String certificateJson = objectMapper.writeValueAsString(badCertificateDto);

        mockMvc.perform(post("/certificate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(certificateJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void editCertificateTest() throws Exception {
        String certificateJson = objectMapper.writeValueAsString(certificateDTO1);
        mockMvc.perform(put("/certificate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(certificateJson))
                .andExpect(status().isOk());
        verify(certificateService).update(any(CertificateDTO.class));
    }

    @Test
    public void notValidEditCertificateTest() throws Exception {
        CertificateDTO badCertificateDto = certificateDTO1;
        badCertificateDto.setPhoto(null);
        String certificateJson = objectMapper.writeValueAsString(badCertificateDto);

        mockMvc.perform(put("/certificate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(certificateJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void deleteCertificateTest() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/certificate/"+ id))
                .andExpect(status().isOk());
        verify(certificateService).delete(id);
    }
}