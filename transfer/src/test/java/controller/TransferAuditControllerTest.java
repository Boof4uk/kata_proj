package controller;

import com.bank.transfer.controller.TransferAuditController;
import com.bank.transfer.dto.TransferAuditDTO;
import com.bank.transfer.serviceImpl.TransferAuditServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = TransferAuditController.class)
@ContextConfiguration(classes = TransferAuditController.class)
public class TransferAuditControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransferAuditServiceImpl service;

    @InjectMocks
    private TransferAuditController controller;

    private TransferAuditDTO transferAuditDTO = new TransferAuditDTO(1L, "hello", "world", "John", "Brown", LocalDateTime.of(2012, 12, 6, 12, 20), LocalDateTime.of(2020, 5, 23, 15, 33), "newJson", "json");
    private TransferAuditDTO transferAuditDTO4 = new TransferAuditDTO(2L, "goodbye", "world", "John", "Brown", LocalDateTime.of(2012, 12, 6, 12, 20), LocalDateTime.of(2020, 5, 23, 15, 33), "newJson", "json");
    private TransferAuditDTO transferAuditDTO2 = new TransferAuditDTO(0L, "hello", "world", "John", "Brown", LocalDateTime.of(2012, 12, 6, 12, 20), LocalDateTime.of(2020, 5, 23, 15, 33), "newJson", "json");


    @Test
    public void getAllTest() throws Exception {
        service.add(transferAuditDTO);
        service.add(transferAuditDTO4);
        List<TransferAuditDTO> list = service.all();

        when(service.all()).thenReturn(list);

        mvc.perform(get("/api/transfer-audit")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void showByIdTest() throws Exception {
        service.add(transferAuditDTO);
        when(service.showById(transferAuditDTO.getId())).thenReturn(transferAuditDTO);

        mvc.perform(get("/api/transfer-audit/" + transferAuditDTO.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addTest() throws Exception {
        when(service.add(transferAuditDTO)).thenReturn(transferAuditDTO);

        mvc.perform(post("/api/transfer-audit").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updateTest() throws Exception {

        when(service.update(transferAuditDTO)).thenReturn(transferAuditDTO);
        mvc.perform(put("/api/transfer-audit/" + transferAuditDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().findAndRegisterModules().writeValueAsString(transferAuditDTO)))
                .andExpect(status().isOk())
                .andReturn();
        mvc.perform(put("/api/transfer-audit/" + transferAuditDTO2.getId()).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().findAndRegisterModules().writeValueAsString(transferAuditDTO2)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void deleteByIdTest() throws Exception {

        service.add(transferAuditDTO);
        service.delete(transferAuditDTO.getId());
        mvc.perform(delete("/api/transfer-audit/" + transferAuditDTO.getId()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @ParameterizedTest
    @MethodSource("dataTest")
    public void invalidIdTest(Long id, BindingResult bindingResult) {
        assertEquals(ResponseEntity.badRequest().body("Invalid input data"), controller.delete(id, bindingResult));
    }

    public static Stream<Arguments> dataTest() {
        return Stream.of(Arguments.of(0L, null));
    }
}
