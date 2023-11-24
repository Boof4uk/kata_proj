package controller;

import com.bank.transfer.controller.AccountTransferController;
import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.repository.AccountTransferRepository;
import com.bank.transfer.serviceImpl.AccountTransferServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
@WebMvcTest(controllers = AccountTransferController.class)
@ContextConfiguration(classes = AccountTransferController.class)
public class AccountTransferControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountTransferServiceImpl service;

    @Autowired
    private AccountTransferController controller;

    @MockBean
    AccountTransferRepository accountTransferRepository;
    private AccountTransferDTO accountTransferDTO = new AccountTransferDTO(1L, 2L, new BigDecimal(3), "hello", 4L);
    private AccountTransferDTO accountTransferDTO2 = new AccountTransferDTO(0L, 3L, new BigDecimal(3), "hello", 4L);

    private AccountTransferDTO accountTransferDTO4 = new AccountTransferDTO(2L, 3L, new BigDecimal(3), "hello", 4L);


    @Test
    public void getAllTest() throws Exception {
        service.add(accountTransferDTO);
        service.add(accountTransferDTO4);
        List<AccountTransferDTO> list = service.all();

        when(service.all()).thenReturn(list);

        mvc.perform(get("/api/account-transfer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void showByIdTest() throws Exception {
        service.add(accountTransferDTO);
        when(service.showById(accountTransferDTO.getId())).thenReturn(accountTransferDTO);

        mvc.perform(get("/api/account-transfer/" + accountTransferDTO.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addTest() throws Exception {
        when(service.add(accountTransferDTO)).thenReturn(accountTransferDTO);

        mvc.perform(post("/api/account-transfer").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updateTest() throws Exception {

        when(service.update(accountTransferDTO)).thenReturn(accountTransferDTO);
        mvc.perform(put("/api/account-transfer/" + accountTransferDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(accountTransferDTO)))
                .andExpect(status().isOk())
                .andReturn();
        mvc.perform(put("/api/account-transfer/" + accountTransferDTO2.getId()).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(accountTransferDTO2)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void deleteByIdTest() throws Exception {

        service.add(accountTransferDTO);
        service.delete(accountTransferDTO.getId());
        mvc.perform(delete("/api/account-transfer/" + accountTransferDTO.getId()))
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
