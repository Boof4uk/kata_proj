package com.bank.authorization.service.impl;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.entity.Audit;
import com.bank.authorization.exception.EntityNotFoundException;
import com.bank.authorization.mapper.AuditMapper;
import com.bank.authorization.repository.AuditRepository;
import com.bank.authorization.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AuditServiceImpl implements AuditService {

    private static final String ENTITY_NAME = "Audit";

    private final AuditRepository auditRepository;

    private final AuditMapper auditMapper;

    @Override
    @Transactional
    public AuditDto add(AuditDto auditDto) {
        log.info("Creating audit {}", auditDto);
        try {
            return auditMapper.toDto(auditRepository.save(auditMapper.toEntity(auditDto)));
        } catch (RuntimeException e) {
            log.error("Error creating audit: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<AuditDto> getAll() {
        log.info("Getting all audits");
        try {
            return auditMapper.toDtoList(auditRepository.findAll());
        } catch (RuntimeException e) {
            log.error("Error getting audits: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public AuditDto update(AuditDto auditDto) {
        log.info("Updating audit with id: {} and body: {}", auditDto.getId(), auditDto);
        try {
            if (auditDto.getId() == null) {
                throw new IllegalArgumentException("The given id must not be null!");
            }
            final Audit auditEntity = auditMapper.toEntity(auditDto);
            final Optional<Audit> optionalAudit = auditRepository.findById(auditEntity.getId());
            optionalAudit
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_NAME, auditEntity.getId()));
            auditRepository.save(auditEntity);
            return auditMapper.toDto(auditEntity);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            log.error("Error updating audit with id: {}. Error: {}", auditDto.getId(), e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Deleting audit with id: {}", id);
        try {
            auditRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_NAME, id));
            auditRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error("Error deleting audit with id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public AuditDto getById(Long id) {
        log.info("Getting audit with id: {}", id);
        try {
            final Optional<Audit> auditOptional = auditRepository.findById(id);
            final Audit audit = auditOptional
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_NAME, id));
            return auditMapper.toDto(audit);
        } catch (EntityNotFoundException e) {
            log.error("Error getting audit with id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }
}
