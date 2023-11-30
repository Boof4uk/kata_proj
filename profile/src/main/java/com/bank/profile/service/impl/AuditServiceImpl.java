package com.bank.profile.service.impl;

import com.bank.profile.dto.request.AuditRequestDto;
import com.bank.profile.dto.response.AuditResponseDto;
import com.bank.profile.entity.Audit;
import com.bank.profile.exception.EntityNameExistsException;
import com.bank.profile.exception.ResourceNotFoundException;
import com.bank.profile.mapper.AuditMapper;
import com.bank.profile.repository.AuditRepository;
import com.bank.profile.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {
    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;

    @Override
    public AuditResponseDto create(AuditRequestDto auditRequestDto) {
        log.info("Creating audit with request: {}", auditRequestDto);
        try {
            return auditMapper.toDto(auditRepository.save(auditMapper.toEntity(auditRequestDto)));
        } catch (EntityNameExistsException e) {
            log.error("Error creating audit: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public AuditResponseDto getById(Long id) {
        log.info("Getting audit by id: {}", id);
        try {
            return auditMapper.toDto(auditRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Audit getById", "AuditId", id)));
        } catch (ResourceNotFoundException e) {
            log.error("Error getting audit by id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public AuditResponseDto update(Long id, AuditRequestDto auditRequestDto) {
        log.info("Updating audit with id: {} and request: {}", id, auditRequestDto);
        try {
            auditRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Audit update", "AuditId", id));
            final Audit auditUpdate = auditMapper.toEntity(auditRequestDto);
            auditUpdate.setId(id);
            return auditMapper.toDto(auditRepository.save(auditUpdate));
        } catch (ResourceNotFoundException e) {
            log.error("Error updating audit with id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting audit with id: {}", id);
        try {
            final Audit audit = auditRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Audit delete", "AuditId", id));
            auditRepository.delete(audit);
        } catch (ResourceNotFoundException e) {
            log.error("Error deleting audit with id: {}. Error: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<AuditResponseDto> getAll() {
        log.info("Getting all audits");

        return auditMapper.toDTOList(auditRepository.findAll());

    }
}
