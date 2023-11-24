package controller;

import com.bank.transfer.controller.PhoneTransferController;
import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.serviceImpl.PhoneTransferServiceImpl;
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

import java.math.BigDecimal;
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
@WebMvcTest(controllers = PhoneTransferController.class)
@ContextConfiguration(classes = PhoneTransferController.class)
public class PhoneTransferControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PhoneTransferServiceImpl service;

    @InjectMocks
    private PhoneTransferController controller;

    private PhoneTransferDTO phoneTransferDTO = new PhoneTransferDTO(1L, 2L, new BigDecimal(3), "hello", 4L);
    private PhoneTransferDTO phoneTransferDTO4 = new PhoneTransferDTO(2L, 3L, new BigDecimal(3), "hello", 4L);
    private PhoneTransferDTO phoneTransferDTO2 = new PhoneTransferDTO(0L, 2L, new BigDecimal(3), "hello", 4L);

    @Test
    public void getAllTest() throws Exception {
        service.add(phoneTransferDTO);
        service.add(phoneTransferDTO4);
        List<PhoneTransferDTO> list = service.all();

        when(service.all()).thenReturn(list);

        mvc.perform(get("/api/phone-transfer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void showByIdTest() throws Exception {
        service.add(phoneTransferDTO);
        when(service.showById(phoneTransferDTO.getId())).thenReturn(phoneTransferDTO);

        mvc.perform(get("/api/phone-transfer/" + phoneTransferDTO.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addTest() throws Exception {
        when(service.add(phoneTransferDTO)).thenReturn(phoneTransferDTO);

        mvc.perform(post("/api/phone-transfer").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updateTest() throws Exception {

        when(service.update(phoneTransferDTO)).thenReturn(phoneTransferDTO);
        mvc.perform(put("/api/phone-transfer/" + phoneTransferDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(phoneTransferDTO)))
                .andExpect(status().isOk())
                .andReturn();
        mvc.perform(put("/api/phone-transfer/" + phoneTransferDTO2.getId()).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(phoneTransferDTO2)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void deleteByIdTest() throws Exception {

        service.add(phoneTransferDTO);
        service.delete(phoneTransferDTO.getId());
        mvc.perform(delete("/api/phone-transfer/" + phoneTransferDTO.getId()))
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
