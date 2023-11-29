package com.bank.antifraud.model;

import com.bank.antifraud.dto.SuspiciousPhoneTransfersDTO;
import com.bank.antifraud.entity.SuspiciousPhoneTransfers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SuspiciousPhoneTransfersDtoAndEntityTest {

    private SuspiciousPhoneTransfers entity = new SuspiciousPhoneTransfers(1L, 1L,
            true, true, "string", "string");
    private SuspiciousPhoneTransfersDTO dto = new SuspiciousPhoneTransfersDTO(1L, 1L,
            true, true, "string", "string");

    @Test
    public void dtoTest() {
        SuspiciousPhoneTransfersDTO phoneTransferDTO = new SuspiciousPhoneTransfersDTO();
        SuspiciousPhoneTransfers phoneTransfer = new SuspiciousPhoneTransfers();

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getPhoneTransferId(), entity.getPhoneTransferId());
        assertEquals(dto.isBlocked(), entity.isBlocked());
        assertEquals(dto.isSuspicious(), entity.isSuspicious());
        assertEquals(dto.getBlockedReason(), entity.getBlockedReason());
        assertEquals(dto.getSuspiciousReason(), entity.getSuspiciousReason());

        SuspiciousPhoneTransfersDTO phoneDTO = new SuspiciousPhoneTransfersDTO(entity.getId(),
                entity.getPhoneTransferId(), entity.isBlocked(), entity.isSuspicious(), entity.getBlockedReason(), entity.getSuspiciousReason());
        assertEquals(phoneDTO, dto);
        assertEquals(phoneDTO.toString(), dto.toString());


        SuspiciousPhoneTransfers phone = new SuspiciousPhoneTransfers(dto.getId(),
                entity.getPhoneTransferId(), entity.isBlocked(), entity.isSuspicious(), entity.getBlockedReason(), entity.getSuspiciousReason());
        assertEquals(phone, entity);
        assertEquals(phone.toString(), entity.toString());

        phoneTransfer.setId(entity.getId());
        phoneTransfer.setPhoneTransferId(entity.getPhoneTransferId());
        phoneTransfer.setBlocked(entity.isBlocked());
        phoneTransfer.setSuspicious(entity.isSuspicious());
        phoneTransfer.setBlockedReason(entity.getBlockedReason());
        phoneTransfer.setSuspiciousReason(entity.getSuspiciousReason());


        phoneTransferDTO.setId(dto.getId());
        phoneTransferDTO.setPhoneTransferId(dto.getPhoneTransferId());
        phoneTransferDTO.setBlocked(dto.isBlocked());
        phoneTransferDTO.setSuspicious(dto.isSuspicious());
        phoneTransferDTO.setBlockedReason(dto.getBlockedReason());
        phoneTransferDTO.setSuspiciousReason(dto.getSuspiciousReason());

        assertEquals(dto.hashCode(), phoneDTO.hashCode());
        assertEquals(entity.hashCode(), phone.hashCode());
    }
}
