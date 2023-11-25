package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.service.LicenseService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LicenseController.class)
class LicenseControllerTest {

    @MockBean
    LicenseService licenseService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private LicenseDTO licenseDTO1;
    private LicenseDTO licenseDTO2;

    @BeforeEach
    public void setUp(){
        licenseDTO1 = new LicenseDTO(1L,new byte[1],new BankDetailsDTO());
        licenseDTO2 = new LicenseDTO(2L,new byte[1],new BankDetailsDTO());
    }

    @Test
    public void getAllTest() throws Exception {
        Mockito.when(licenseService.getAll()).thenReturn(List.of(licenseDTO1,licenseDTO2));

        mockMvc.perform(get("/license"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void getLicenseTest() throws Exception {
        Mockito.when(licenseService.find(1L)).thenReturn(licenseDTO1);

        mockMvc.perform(get("/license/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
    @Test
    public void shouldHandleResourceNotFoundExceptionTest() throws Exception {
        Long nonExistentId = 5L;
        Mockito.when(licenseService.find(nonExistentId)).thenThrow(new EntityNotFoundException("License not found"));

        mockMvc.perform(get("/license/" + nonExistentId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("License not found"));
    }

    @Test
    public void createLicenseTest() throws Exception {
        String licenseJson = objectMapper.writeValueAsString(licenseDTO1);
        mockMvc.perform(post("/license")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(licenseJson))
                .andExpect(status().isOk());
        verify(licenseService).save(any(LicenseDTO.class));
    }

    @Test
    public void notValidCreateLicenseTest() throws Exception {
        LicenseDTO badLicenseDto = licenseDTO1;
        badLicenseDto.setPhoto(null);
        String licenseJson = objectMapper.writeValueAsString(badLicenseDto);

        mockMvc.perform(post("/license")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(licenseJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void editLicenseTest() throws Exception {
        String licenseJson = objectMapper.writeValueAsString(licenseDTO1);
        mockMvc.perform(put("/license")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(licenseJson))
                .andExpect(status().isOk());
        verify(licenseService).update(any(LicenseDTO.class));
    }

    @Test
    public void notValidEditLicenseTest() throws Exception {
        LicenseDTO badLicenseDto = licenseDTO1;
        badLicenseDto.setPhoto(null);
        String licenseJson = objectMapper.writeValueAsString(badLicenseDto);

        mockMvc.perform(put("/license")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(licenseJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void deleteLicenseTest() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/license/"+ id))
                .andExpect(status().isOk());
        verify(licenseService).delete(id);
    }
}