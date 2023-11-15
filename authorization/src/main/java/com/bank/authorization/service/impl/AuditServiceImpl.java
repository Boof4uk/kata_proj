package com.bank.authorization.service.impl;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.entity.Audit;
import com.bank.authorization.mapper.AuditMapper;
import com.bank.authorization.repository.AuditRepository;
import com.bank.authorization.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    private final AuditMapper auditMapper;

    @Override
    @Transactional
    public AuditDto add(AuditDto auditDto) {
        Audit audit = auditRepository.save(auditMapper.toEntity(auditDto));
        return auditMapper.toDto(audit);
    }

    @Override
    public List<AuditDto> getAll() {
        return auditMapper.toDtoList(auditRepository.findAll());
    }

    @Override
    @Transactional
    public AuditDto update(AuditDto auditDto) {
        Audit auditEntity = auditMapper.toEntity(auditDto);
        Audit audit = auditRepository.findById(auditEntity.getId())
                .orElseThrow(() -> new RuntimeException("Audit ID not found. id: " + auditEntity.getId()));
        return auditMapper.toDto(auditRepository.save(audit));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Audit audit = auditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Audit ID not found. id: " + id));
        auditRepository.deleteById(audit.getId());
    }

    @Override
    public AuditDto getById(Long id) {
        Audit audit = auditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Audit ID not found. id: " + id));
        return auditMapper.toDto(audit);
    }
}
