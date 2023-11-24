package com.bank.transfer.service;

import com.bank.transfer.dto.AccountTransferDTO;

import java.util.List;

/**
 * Интерфейс сервиса для сущности AccountTransfer
 */

public interface AccountTransferService {
    AccountTransferDTO add(AccountTransferDTO accountTransferDTO);

    List<AccountTransferDTO> all();

    AccountTransferDTO update(AccountTransferDTO accountTransferDTO);

    void delete(Long id);

    AccountTransferDTO showById(Long id);
}
