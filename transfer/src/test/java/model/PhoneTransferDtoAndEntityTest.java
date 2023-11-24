package model;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.entity.PhoneTransfer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PhoneTransferDtoAndEntityTest {

    private PhoneTransferDTO dto = new PhoneTransferDTO(1L, 2L, new BigDecimal(3), "hello", 4L);
    private PhoneTransfer entity = new PhoneTransfer(1L, 2L, new BigDecimal(3), "hello", 4L);


    @Test
    void dto() {
        PhoneTransferDTO accountTransferDTO = new PhoneTransferDTO();
        PhoneTransfer accountTransfer = new PhoneTransfer();

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getAmount(), entity.getAmount());
        assertEquals(dto.getPhoneNumber(), entity.getPhoneNumber());
        assertEquals(dto.getAccountDetailsId(), entity.getAccountDetailsId());
        assertEquals(dto.getPurpose(), entity.getPurpose());

        PhoneTransferDTO transferDTO = new PhoneTransferDTO(entity.getId(), entity.getPhoneNumber(), entity.getAmount(), entity.getPurpose(), entity.getAccountDetailsId());
        assertEquals(transferDTO, dto);
        assertEquals(transferDTO.toString(), dto.toString());


        PhoneTransfer transfer = new PhoneTransfer(dto.getId(), dto.getPhoneNumber(), dto.getAmount(), dto.getPurpose(), dto.getAccountDetailsId());
        assertEquals(transfer, entity);
        assertEquals(transfer.toString(), entity.toString());

        accountTransfer.setId(entity.getId());
        accountTransfer.setAmount(entity.getAmount());
        accountTransfer.setPhoneNumber(entity.getPhoneNumber());
        accountTransfer.setPurpose(entity.getPurpose());
        accountTransfer.setAccountDetailsId(entity.getAccountDetailsId());


        accountTransferDTO.setId(dto.getId());
        accountTransferDTO.setAmount(dto.getAmount());
        accountTransferDTO.setPhoneNumber(dto.getPhoneNumber());
        accountTransferDTO.setPurpose(dto.getPurpose());
        accountTransferDTO.setAccountDetailsId(dto.getAccountDetailsId());


        assertEquals(dto.hashCode(), transferDTO.hashCode());
        assertEquals(entity.hashCode(), transfer.hashCode());
    }

}