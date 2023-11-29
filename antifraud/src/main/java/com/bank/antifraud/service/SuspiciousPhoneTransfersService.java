package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousPhoneTransfersDTO;

import java.util.List;

/**
 * Интерфейс сервиса для сущности SuspiciousPhoneTransfers
 */
public interface SuspiciousPhoneTransfersService {
    SuspiciousPhoneTransfersDTO add(SuspiciousPhoneTransfersDTO suspiciousPhoneTransfersDTO);

    List<SuspiciousPhoneTransfersDTO> getAll();

    SuspiciousPhoneTransfersDTO update(Long id, SuspiciousPhoneTransfersDTO suspiciousPhoneTransfersDTO);

    void delete(Long id);

    SuspiciousPhoneTransfersDTO getById(Long id);
}
