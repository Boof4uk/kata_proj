package com.bank.antifraud.service;
import com.bank.antifraud.dto.AntifraudAuditDTO;
import java.util.List;
/**
 * Интерфейс сервиса для сущности AntifraudAudit
 */
public interface AntifraudAuditService {
    AntifraudAuditDTO add(AntifraudAuditDTO suspiciousAuditDTO);

    List<AntifraudAuditDTO> getAll();

    AntifraudAuditDTO update(AntifraudAuditDTO suspiciousAuditDTO);

    void delete(Long id);

    AntifraudAuditDTO getById(Long id);
}
