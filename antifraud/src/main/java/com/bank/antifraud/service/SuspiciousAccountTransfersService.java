package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousAccountTransfersDTO;

import java.util.List;

/**
 * Интерфейс сервиса для сущности SuspiciousAccountTransfers
 */
public interface SuspiciousAccountTransfersService {
    SuspiciousAccountTransfersDTO add(SuspiciousAccountTransfersDTO suspiciousaccountTransfersDTO);

    List<SuspiciousAccountTransfersDTO> getAll();

    SuspiciousAccountTransfersDTO update(Long id, SuspiciousAccountTransfersDTO suspiciousaccountTransfersDTO);

    void delete(Long id);

    SuspiciousAccountTransfersDTO getById(Long id);
}
