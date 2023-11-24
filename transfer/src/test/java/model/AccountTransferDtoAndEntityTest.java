package model;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AccountTransferDtoAndEntityTest {

    private AccountTransferDTO dto = new AccountTransferDTO(1L, 2L, new BigDecimal(3), "hello", 4L);
    private AccountTransfer entity = new AccountTransfer(1L, 2L, new BigDecimal(3), "hello", 4L);


    @Test
    public void dtoTest() {
        AccountTransferDTO accountTransferDTO = new AccountTransferDTO();
        AccountTransfer accountTransfer = new AccountTransfer();

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getAmount(), entity.getAmount());
        assertEquals(dto.getAccountNumber(), entity.getAccountNumber());
        assertEquals(dto.getAccountDetailsId(), entity.getAccountDetailsId());
        assertEquals(dto.getPurpose(), entity.getPurpose());

        AccountTransferDTO transferDTO = new AccountTransferDTO(entity.getId(), entity.getAccountNumber(), entity.getAmount(), entity.getPurpose(), entity.getAccountDetailsId());
        assertEquals(transferDTO, dto);
        assertEquals(transferDTO.toString(), dto.toString());


        AccountTransfer transfer = new AccountTransfer(dto.getId(), dto.getAccountNumber(), dto.getAmount(), dto.getPurpose(), dto.getAccountDetailsId());
        assertEquals(transfer, entity);
        assertEquals(transfer.toString(), entity.toString());

        accountTransfer.setId(entity.getId());
        accountTransfer.setAmount(entity.getAmount());
        accountTransfer.setAccountNumber(entity.getAccountNumber());
        accountTransfer.setPurpose(entity.getPurpose());
        accountTransfer.setAccountDetailsId(entity.getAccountDetailsId());


        accountTransferDTO.setId(dto.getId());
        accountTransferDTO.setAmount(dto.getAmount());
        accountTransferDTO.setAccountNumber(dto.getAccountNumber());
        accountTransferDTO.setPurpose(dto.getPurpose());
        accountTransferDTO.setAccountDetailsId(dto.getAccountDetailsId());

        assertEquals(dto.hashCode(), transferDTO.hashCode());
        assertEquals(entity.hashCode(), transfer.hashCode());

    }
}
