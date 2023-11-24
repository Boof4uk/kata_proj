package com.bank.antifraud.service;


import com.bank.antifraud.dto.SuspiciousCardTransferDTO;

import java.util.List;
/**
 * Интерфейс сервиса для сущности SuspiciousCardTransfer
 */
public interface SuspiciousCardTransferService {
    SuspiciousCardTransferDTO add(SuspiciousCardTransferDTO suspiciousCardTransferDTO);

    List<SuspiciousCardTransferDTO> getAll();

    SuspiciousCardTransferDTO update(Long id,SuspiciousCardTransferDTO suspiciousCardTransferDTO);

    void delete(Long id);

    SuspiciousCardTransferDTO getById(Long id);
}
