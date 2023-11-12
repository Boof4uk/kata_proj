package com.bank.transfer.service;

import com.bank.transfer.dto.TransferAuditDTO;

import java.util.List;

/**
 * Интерфейс сервиса для сущности TransferAudit
 */

public interface TransferAuditService {

    TransferAuditDTO add(TransferAuditDTO transferAuditDTO);

    List<TransferAuditDTO> all();

    TransferAuditDTO update(TransferAuditDTO transferAuditDTO);

    void delete(Long id);

    TransferAuditDTO showById(Long id);
}
