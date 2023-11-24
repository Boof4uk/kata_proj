package com.bank.transfer.service;

import com.bank.transfer.dto.PhoneTransferDTO;

import java.util.List;

/**
 * Интерфейс сервиса для сущности PhoneTransfer
 */

public interface PhoneTransferService {

    PhoneTransferDTO add(PhoneTransferDTO phoneTransferDTO);

    List<PhoneTransferDTO> all();

    PhoneTransferDTO update(PhoneTransferDTO phoneTransferDTO);

    void delete(Long id);

    PhoneTransferDTO showById(Long id);
}
