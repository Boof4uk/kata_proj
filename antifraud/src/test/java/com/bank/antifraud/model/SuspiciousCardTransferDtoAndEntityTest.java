package com.bank.antifraud.model;

import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.entity.SuspiciousCardTransfer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SuspiciousCardTransferDtoAndEntityTest {
    private SuspiciousCardTransfer entity = new SuspiciousCardTransfer(1L, 1L,
            true, true, "string", "string");
    private SuspiciousCardTransferDTO dto = new SuspiciousCardTransferDTO(1L, 1L,
            true, true, "string", "string");

    @Test
    public void dtoTest() {
        SuspiciousCardTransferDTO cardTransferDTO = new SuspiciousCardTransferDTO();
        SuspiciousCardTransfer cardTransfer = new SuspiciousCardTransfer();

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getCardTransferId(), entity.getCardTransferId());
        assertEquals(dto.isBlocked(), entity.isBlocked());
        assertEquals(dto.isSuspicious(), entity.isSuspicious());
        assertEquals(dto.getBlockedReason(), entity.getBlockedReason());
        assertEquals(dto.getSuspiciousReason(), entity.getSuspiciousReason());

        SuspiciousCardTransferDTO cardDTO = new SuspiciousCardTransferDTO(entity.getId(),
                entity.getCardTransferId(), entity.isBlocked(), entity.isSuspicious(), entity.getBlockedReason(), entity.getSuspiciousReason());
        assertEquals(cardDTO, dto);
        assertEquals(cardDTO.toString(), dto.toString());


        SuspiciousCardTransfer card = new SuspiciousCardTransfer(dto.getId(),
                entity.getCardTransferId(), entity.isBlocked(), entity.isSuspicious(), entity.getBlockedReason(), entity.getSuspiciousReason());
        assertEquals(card, entity);
        assertEquals(card.toString(), entity.toString());

        cardTransfer.setId(entity.getId());
        cardTransfer.setCardTransferId(entity.getCardTransferId());
        cardTransfer.setBlocked(entity.isBlocked());
        cardTransfer.setSuspicious(entity.isSuspicious());
        cardTransfer.setBlockedReason(entity.getBlockedReason());
        cardTransfer.setSuspiciousReason(entity.getSuspiciousReason());


        cardTransferDTO.setId(dto.getId());
        cardTransferDTO.setCardTransferId(dto.getCardTransferId());
        cardTransferDTO.setBlocked(dto.isBlocked());
        cardTransferDTO.setSuspicious(dto.isSuspicious());
        cardTransferDTO.setBlockedReason(dto.getBlockedReason());
        cardTransferDTO.setSuspiciousReason(dto.getSuspiciousReason());

        assertEquals(dto.hashCode(), cardDTO.hashCode());
        assertEquals(entity.hashCode(), card.hashCode());

    }
}
