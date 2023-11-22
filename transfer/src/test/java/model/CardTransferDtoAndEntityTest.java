package model;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.entity.CardTransfer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CardTransferDtoAndEntityTest {

    private CardTransferDTO dto = new CardTransferDTO(1L, 2L, new BigDecimal(3), "hello", 4L);
    private CardTransfer entity = new CardTransfer(1L, 2L, new BigDecimal(3), "hello", 4L);


    @Test
    void dto() {
        CardTransferDTO accountTransferDTO = new CardTransferDTO();
        CardTransfer accountTransfer = new CardTransfer();

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getAmount(), entity.getAmount());
        assertEquals(dto.getCardNumber(), entity.getCardNumber());
        assertEquals(dto.getAccountDetailsId(), entity.getAccountDetailsId());
        assertEquals(dto.getPurpose(), entity.getPurpose());

        CardTransferDTO transferDTO = new CardTransferDTO(entity.getId(), entity.getCardNumber(), entity.getAmount(), entity.getPurpose(), entity.getAccountDetailsId());
        assertEquals(transferDTO, dto);
        assertEquals(transferDTO.toString(), dto.toString());


        CardTransfer transfer = new CardTransfer(dto.getId(), dto.getCardNumber(), dto.getAmount(), dto.getPurpose(), dto.getAccountDetailsId());
        assertEquals(transfer, entity);
        assertEquals(transfer.toString(), entity.toString());

        accountTransfer.setId(entity.getId());
        accountTransfer.setAmount(entity.getAmount());
        accountTransfer.setCardNumber(entity.getCardNumber());
        accountTransfer.setPurpose(entity.getPurpose());
        accountTransfer.setAccountDetailsId(entity.getAccountDetailsId());

        accountTransferDTO.setId(dto.getId());
        accountTransferDTO.setAmount(dto.getAmount());
        accountTransferDTO.setCardNumber(dto.getCardNumber());
        accountTransferDTO.setPurpose(dto.getPurpose());
        accountTransferDTO.setAccountDetailsId(dto.getAccountDetailsId());

        assertEquals(dto.hashCode(), transferDTO.hashCode());
        assertEquals(entity.hashCode(), transfer.hashCode());
    }

}