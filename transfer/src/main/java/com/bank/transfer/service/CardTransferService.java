package com.bank.transfer.service;

import com.bank.transfer.dto.CardTransferDTO;

import java.util.List;

/**
 * Интерфейс сервиса для сущности CardTransfer
 */

public interface CardTransferService {

    CardTransferDTO add(CardTransferDTO cardTransferDTO);

    List<CardTransferDTO> all();

    CardTransferDTO update(CardTransferDTO cardTransferDTO);

    void delete(Long id);

    CardTransferDTO showById(Long id);
}
