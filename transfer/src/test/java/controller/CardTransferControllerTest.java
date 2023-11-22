package controller;

import com.bank.transfer.controller.CardTransferController;
import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.serviceImpl.CardTransferServiceImpl;
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
@WebMvcTest(controllers = CardTransferController.class)
@ContextConfiguration(classes = CardTransferController.class)
public class CardTransferControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CardTransferServiceImpl service;

    @InjectMocks
    private CardTransferController controller;

    private CardTransferDTO cardTransferDTO = new CardTransferDTO(1L, 2L, new BigDecimal(3), "hello", 4L);
    private CardTransferDTO cardTransferDTO4 = new CardTransferDTO(2L, 3L, new BigDecimal(3), "hello", 4L);
    private CardTransferDTO cardTransferDTO2 = new CardTransferDTO(0L, 2L, new BigDecimal(3), "hello", 4L);


    @Test
    public void getAllTest() throws Exception {
        service.add(cardTransferDTO);
        service.add(cardTransferDTO4);
        List<CardTransferDTO> list = service.all();

        when(service.all()).thenReturn(list);

        mvc.perform(get("/api/card-transfer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void showByIdTest() throws Exception {
        service.add(cardTransferDTO);
        when(service.showById(cardTransferDTO.getId())).thenReturn(cardTransferDTO);

        mvc.perform(get("/api/card-transfer/" + cardTransferDTO.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addTest() throws Exception {
        when(service.add(cardTransferDTO)).thenReturn(cardTransferDTO);

        mvc.perform(post("/api/card-transfer").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updateTest() throws Exception {

        when(service.update(cardTransferDTO)).thenReturn(cardTransferDTO);
        mvc.perform(put("/api/card-transfer/" + cardTransferDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(cardTransferDTO)))
                .andExpect(status().isOk())
                .andReturn();
        mvc.perform(put("/api/card-transfer/" + cardTransferDTO2.getId()).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(cardTransferDTO2)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void deleteByIdTest() throws Exception {

        service.add(cardTransferDTO);
        service.delete(cardTransferDTO.getId());
        mvc.perform(delete("/api/card-transfer/" + cardTransferDTO.getId()))
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
