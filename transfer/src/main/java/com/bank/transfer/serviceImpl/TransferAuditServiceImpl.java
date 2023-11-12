package com.bank.transfer.serviceImpl;

import com.bank.transfer.dto.TransferAuditDTO;
import com.bank.transfer.entity.TransferAudit;
import com.bank.transfer.mapper.TransferAuditMapper;
import com.bank.transfer.repository.TransferAuditRepository;
import com.bank.transfer.service.TransferAuditService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса TransferAuditService для сущности TransferAudit
 */

@Service
public class TransferAuditServiceImpl implements TransferAuditService {

    /**
     * Репозиторий для сущности TransferAudit
     */

    private final TransferAuditRepository transferAuditRepository;

    /**
     * Маппер для сущности TransferAudit
     */

    private final TransferAuditMapper transferAuditMapper;

    public TransferAuditServiceImpl(TransferAuditRepository transferAuditRepository, TransferAuditMapper transferAuditMapper) {
        this.transferAuditRepository = transferAuditRepository;
        this.transferAuditMapper = transferAuditMapper;
    }

    /**
     * Добавить аудит
     * @param transferAuditDTO объект, содержащий данные для аудита
     * @return объект TransferAuditDTO
     */

    @Override
    public TransferAuditDTO add(TransferAuditDTO transferAuditDTO) {
        return transferAuditMapper.toDto(transferAuditRepository.save(transferAuditMapper.toEntity(transferAuditDTO)));
    }

    /**
     * Получить все аудиты
     * @return список аудитов (объектов TransferAuditDTO)
     */

    @Override
    public List<TransferAuditDTO> all() {
        List<TransferAudit> list = transferAuditRepository.findAll();
        return transferAuditMapper.toDTOList(list);
    }

    /**
     * Обновить данные для аудита
     * @param transferAuditDTO объект, содержащий данные для аудита
     * @return объект TransferAuditDTO
     */

    @Override
    public TransferAuditDTO update(TransferAuditDTO transferAuditDTO) {
        TransferAudit transferAudit = transferAuditMapper.toEntity(transferAuditDTO);
        transferAuditRepository.save(transferAudit);
        return transferAuditMapper.toDto(transferAudit);
    }

    /**
     * Удалить аудит по идентификатору
     * @param id идентификатор аудита
     */

    @Override
    public void delete(Long id) {
        transferAuditRepository.deleteById(id);
    }

    /**
     * Получить аудит по идентификатору
     * @param id идентификатор аудита
     * @return объект TransferAuditDTO
     */

    @Override
    public TransferAuditDTO showById(Long id) {
        TransferAudit transferAudit = transferAuditRepository.findById(id).get();
        return transferAuditMapper.toDto(transferAudit);
    }
}
