package com.bank.antifraud.serviceImpl;

import com.bank.antifraud.dto.AntifraudAuditDTO;
import com.bank.antifraud.entity.AntifraudAudit;
import com.bank.antifraud.exception.EntityNotFoundException;
import com.bank.antifraud.mapper.AntifraudAuditMapper;
import com.bank.antifraud.repository.AntifraudAuditRepository;
import com.bank.antifraud.service.AntifraudAuditService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс сервиса для сущности AntifraudAudit
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@NoArgsConstructor(force = true)
@Slf4j
public class AntifraudAuditServiceImpl implements AntifraudAuditService {
    /**
     * Репозиторий для сущности AntifraudAudit
     */
    private final AntifraudAuditRepository antifraudAuditRepository;
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
        AntifraudAudit antifraudAudit = antifraudAuditRepository.save(auditMapper.toEntity(suspiciousAuditDTO));
        return auditMapper.toDTO(antifraudAudit);

    }

    /**
     * Получить все аудиты
     *
     * @return список аудитов (объектов AntifraudAuditDTO)
     */
    @Override
    public List<AntifraudAuditDTO> getAll() {
        List<AntifraudAudit> antifraudAudit = antifraudAuditRepository.findAll();
        return auditMapper.toDtoList(antifraudAudit);
    }

    /**
     * Обновить данные для аудита
     *
     * @param suspiciousAuditDTO объект, содержащий данные для аудитa
     * @return объект AntifraudAuditDTO
     */
    @Override
    public AntifraudAuditDTO update(Long id, AntifraudAuditDTO suspiciousAuditDTO) {
        AntifraudAudit antifraudAudit = antifraudAuditRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AntifraudAudit update", id));
        AntifraudAudit antifraudAuditUpdate = auditMapper.toEntity(suspiciousAuditDTO);
        antifraudAuditUpdate.setId(antifraudAudit.getId());
        return auditMapper.toDTO(antifraudAuditRepository.save(antifraudAuditUpdate));
    }

    /**
     * Удалить аудит по id
     *
     * @param id идентификатор аудита
     */
    @Override
    public void delete(Long id) {
        antifraudAuditRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AntifraudAudit delete", id));
        antifraudAuditRepository.deleteById(id);
    }

    /**
     * Получить аудит по id
     *
     * @param id идентификатор аудита
     * @return объект AntifraudAuditDTO
     */
    @Override
    public AntifraudAuditDTO getById(Long id) {
        AntifraudAudit antifraudAudit = antifraudAuditRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AntifraudAudit getById", id));
        return auditMapper.toDTO(antifraudAudit);
    }
}
