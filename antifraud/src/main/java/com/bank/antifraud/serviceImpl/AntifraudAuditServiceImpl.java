package com.bank.antifraud.serviceImpl;

import com.bank.antifraud.dto.AntifraudAuditDTO;
import com.bank.antifraud.entity.AntifraudAudit;
import com.bank.antifraud.mapper.AntifraudAuditMapper;
import com.bank.antifraud.repository.AntifraudAuditRepository;
import com.bank.antifraud.service.AntifraudAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Класс сервиса для сущности AntifraudAudit
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AntifraudAuditServiceImpl implements AntifraudAuditService {
    /**
     * Репозиторий для сущности AntifraudAudit
     */
    private final AntifraudAuditRepository auditRepository;
    /**
     * Маппер для сущности AntifraudAudit
     */
    private final AntifraudAuditMapper auditMapper;

    /**
     * Добавить аудит
     *
     * @param suspiciousAuditDTO объект, содержащий данные для аудита
     * @return объект AntifraudAuditDTO
     */
    @Override
    public AntifraudAuditDTO add(AntifraudAuditDTO suspiciousAuditDTO) {
        AntifraudAudit antifraudAudit = auditRepository.save(auditMapper.toEntity(suspiciousAuditDTO));
        return auditMapper.toDTO(antifraudAudit);

    }

    /**
     * Получить все аудиты
     *
     * @return список аудитов (объектов AntifraudAuditDTO)
     */
    @Override
    public List<AntifraudAuditDTO> getAll() {
        return auditMapper.toDtoList(auditRepository.findAll());
    }

    /**
     * Обновить данные для аудита
     *
     * @param suspiciousAuditDTO объект, содержащий данные для аудитa
     * @return объект AntifraudAuditDTO
     */
    @Override
    public AntifraudAuditDTO update(AntifraudAuditDTO suspiciousAuditDTO) {
        AntifraudAudit antifraudAudit = auditRepository.findById(suspiciousAuditDTO.getId())
                .orElseThrow(() -> new RuntimeException("AccountTransfer not found with id: " + suspiciousAuditDTO.getId()));
        return auditMapper.toDTO(auditRepository.save(antifraudAudit));
    }

    /**
     * Удалить аудит по id
     *
     * @param id идентификатор аудита
     */
    @Override
    public void delete(Long id) {
        auditRepository.deleteById(id);
    }

    /**
     * Получить аудит по id
     *
     * @param id идентификатор аудита
     * @return объект AntifraudAuditDTO
     */
    @Override
    public AntifraudAuditDTO getById(Long id) {
        AntifraudAudit antifraudAudit = auditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AccountTransfer not found with id: " + id));
        return auditMapper.toDTO(antifraudAudit);
    }
}
