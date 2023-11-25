package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.service.AtmService;
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
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AtmController.class)
class AtmControllerTest {

    @MockBean
    AtmService atmService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private AtmDTO atmDTO1;
    private AtmDTO atmDTO2;
    @BeforeEach
    public void setUp(){
        atmDTO1 = new AtmDTO(1L,
                "адрес",
                LocalTime.of(10,0),
                LocalTime.of(20,0),
                true,
                null);
        atmDTO2 = new AtmDTO(2L,
                "адрес2",
                LocalTime.of(11,0),
                LocalTime.of(21,0),
                true,
                null);
    }

    @Test
    public void getAllTest() throws Exception {
        Mockito.when(atmService.getAll()).thenReturn(List.of(atmDTO1,atmDTO2));

        mockMvc.perform(get("/atm"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void getAtmTest() throws Exception {
        Mockito.when(atmService.find(1L)).thenReturn(atmDTO1);

        mockMvc.perform(get("/atm/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void shouldHandleResourceNotFoundExceptionTest() throws Exception {
        Long nonExistentId = 5L;
        Mockito.when(atmService.find(nonExistentId)).thenThrow(new EntityNotFoundException("Atm not found"));

        mockMvc.perform(get("/atm/" + nonExistentId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Atm not found"));
    }
    @Test
    public void createAtmTest() throws Exception {
        String atmJson = objectMapper.writeValueAsString(atmDTO1);
        mockMvc.perform(post("/atm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atmJson))
                .andExpect(status().isOk());
        verify(atmService).save(any(AtmDTO.class));
    }

    @Test
    public void notValidCreateAtmTest() throws Exception {
        AtmDTO badAtmDto = atmDTO2;
        badAtmDto.setAddress(null);
        String atmJson = objectMapper.writeValueAsString(badAtmDto);

        mockMvc.perform(post("/atm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atmJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void editAtmTest() throws Exception {
        String atmJson = objectMapper.writeValueAsString(atmDTO2);
        mockMvc.perform(put("/atm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atmJson))
                .andExpect(status().isOk());
        verify(atmService).update(any(AtmDTO.class));
    }

    @Test
    public void notValidEditAtmTest() throws Exception {
        AtmDTO badAtmDto = atmDTO2;
        badAtmDto.setAddress(null);
        String atmJson = objectMapper.writeValueAsString(badAtmDto);

        mockMvc.perform(put("/atm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atmJson))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void deleteAtmTest() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/atm/"+ id))
                .andExpect(status().isOk());
        verify(atmService).delete(id);
    }
}