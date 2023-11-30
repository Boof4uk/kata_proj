package com.bank.antifraud.model;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDTO;
import com.bank.antifraud.entity.SuspiciousAccountTransfers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SuspiciousAccountTransfersDtoAndEntityTest {
    private SuspiciousAccountTransfers entity = new SuspiciousAccountTransfers(1L, 1L,
            true, true, "string", "string");
    private SuspiciousAccountTransfersDTO dto = new SuspiciousAccountTransfersDTO(1L, 1L,
            true, true, "string", "string");

    @Test
    public void dtoTest() {
        SuspiciousAccountTransfersDTO accountTransferDTO = new SuspiciousAccountTransfersDTO();
        SuspiciousAccountTransfers accountTransfer = new SuspiciousAccountTransfers();

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getAccountTransferId(), entity.getAccountTransferId());
        assertEquals(dto.isBlocked(), entity.isBlocked());
        assertEquals(dto.isSuspicious(), entity.isSuspicious());
        assertEquals(dto.getBlockedReason(), entity.getBlockedReason());
        assertEquals(dto.getSuspiciousReason(), entity.getSuspiciousReason());

        SuspiciousAccountTransfersDTO accountDTO = new SuspiciousAccountTransfersDTO(entity.getId(),
                entity.getAccountTransferId(), entity.isBlocked(), entity.isSuspicious(), entity.getBlockedReason(), entity.getSuspiciousReason());
        assertEquals(accountDTO, dto);
        assertEquals(accountDTO.toString(), dto.toString());


        SuspiciousAccountTransfers account = new SuspiciousAccountTransfers(dto.getId(),
                entity.getAccountTransferId(), entity.isBlocked(), entity.isSuspicious(), entity.getBlockedReason(), entity.getSuspiciousReason());
        assertEquals(account, entity);
        assertEquals(account.toString(), entity.toString());

        accountTransfer.setId(entity.getId());
        accountTransfer.setAccountTransferId(entity.getAccountTransferId());
        accountTransfer.setBlocked(entity.isBlocked());
        accountTransfer.setSuspicious(entity.isSuspicious());
        accountTransfer.setBlockedReason(entity.getBlockedReason());
        accountTransfer.setSuspiciousReason(entity.getSuspiciousReason());


        accountTransferDTO.setId(dto.getId());
        accountTransferDTO.setAccountTransferId(dto.getAccountTransferId());
        accountTransferDTO.setBlocked(dto.isBlocked());
        accountTransferDTO.setSuspicious(dto.isSuspicious());
        accountTransferDTO.setBlockedReason(dto.getBlockedReason());
        accountTransferDTO.setSuspiciousReason(dto.getSuspiciousReason());

        assertEquals(dto.hashCode(), accountDTO.hashCode());
        assertEquals(entity.hashCode(), account.hashCode());

    }
}
