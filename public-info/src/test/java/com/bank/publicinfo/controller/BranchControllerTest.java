package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.service.BranchService;
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

@WebMvcTest(BranchController.class)
class BranchControllerTest {

    @MockBean
    BranchService branchService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private BranchDTO branchDTO1;
    private BranchDTO branchDTO2;

    @BeforeEach
    public void setUp(){
        branchDTO1 = new BranchDTO(1L,
                "address",
                9999999L,
                "city",
                LocalTime.of(10,0),
                LocalTime.of(20,0));
        branchDTO2 = new BranchDTO(1L,
                "address",
                8888888888L,
                "city",
                LocalTime.of(11,0),
                LocalTime.of(21,0));
    }

    @Test
    void getAllTest() throws Exception {
        Mockito.when(branchService.getAll()).thenReturn(List.of(branchDTO1,branchDTO2));

        mockMvc.perform(get("/branch"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void getCBranchTest() throws Exception {
        Mockito.when(branchService.find(1L)).thenReturn(branchDTO1);

        mockMvc.perform(get("/branch/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void shouldHandleResourceNotFoundExceptionTest() throws Exception {
        Long nonExistentId = 5L;
        Mockito.when(branchService.find(nonExistentId)).thenThrow(new EntityNotFoundException("Branch not found"));

        mockMvc.perform(get("/branch/" + nonExistentId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Branch not found"));
    }

    @Test
    public void createBranchTest() throws Exception {
        String branchJson = objectMapper.writeValueAsString(branchDTO1);
        mockMvc.perform(post("/branch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(branchJson))
                .andExpect(status().isOk());
        verify(branchService).save(any(BranchDTO.class));
    }

    @Test
    public void notValidCreateBranchTest() throws Exception {
        BranchDTO badBranchDto = branchDTO1;
        badBranchDto.setAddress(null);
        String branchJson = objectMapper.writeValueAsString(badBranchDto);

        mockMvc.perform(post("/branch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(branchJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void editBranchTest() throws Exception {
        String branchJson = objectMapper.writeValueAsString(branchDTO1);
        mockMvc.perform(put("/branch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(branchJson))
                .andExpect(status().isOk());
        verify(branchService).update(any(BranchDTO.class));
    }

    @Test
    public void notValidEditBranchTest() throws Exception {
        BranchDTO badBranchDto = branchDTO1;
        badBranchDto.setAddress(null);
        String branchJson = objectMapper.writeValueAsString(badBranchDto);

        mockMvc.perform(put("/branch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(branchJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void deleteBranchTest() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/branch/"+ id))
                .andExpect(status().isOk());
        verify(branchService).delete(id);
    }
}