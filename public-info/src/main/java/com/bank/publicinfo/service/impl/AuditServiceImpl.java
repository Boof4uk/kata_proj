package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.mapper.AuditMapper;
import com.bank.publicinfo.repository.AuditRepository;
import com.bank.publicinfo.service.AuditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@AllArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditMapper auditMapper;
    private final AuditRepository auditRepository;

    @Transactional(readOnly = true)
    public List<AuditDTO> getAll() {
        return auditRepository.findAll().stream().map(auditMapper::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    public void save(AuditDTO auditDTO) {
        auditRepository.save(auditMapper.dtoToEntity(auditDTO));
    }

    @Transactional(readOnly = true)
    public AuditDTO find(Long id) {
        try {
            Audit audit = auditRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Audit not found"));
            return auditMapper.entityToDto(audit);
        } catch (EntityNotFoundException e) {
            log.error("Error getting audit with id: {}. Error:{}", id, e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void update(AuditDTO auditDTO) {
        try {
            auditRepository.findById(auditDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Audit not found"));
            auditRepository.save(auditMapper.dtoToEntity(auditDTO));
        } catch (EntityNotFoundException e) {
            log.error("Error updating audit with id: {}. Error:{}", auditDTO.getId(), e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            auditRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Audit not found"));
            auditRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error("Error deleting audit with id: {}. Error:{}", id, e.getMessage());
            throw e;
        }
    }

}
