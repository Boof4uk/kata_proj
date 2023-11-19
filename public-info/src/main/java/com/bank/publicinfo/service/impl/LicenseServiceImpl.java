package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.mapper.LicenseMapper;
import com.bank.publicinfo.repository.LicenseRepository;
import com.bank.publicinfo.service.LicenseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class LicenseServiceImpl implements LicenseService {

    private final LicenseMapper licenseMapper;
    private final LicenseRepository licenseRepository;

    @Transactional(readOnly = true)
    public List<LicenseDTO> getAll() {
        return licenseRepository.findAll().stream().map(licenseMapper::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    public void save(LicenseDTO licenseDTO) {
        licenseRepository.save(licenseMapper.dtoToEntity(licenseDTO));
    }

    @Transactional(readOnly = true)
    public LicenseDTO find(Long id) {
        try {
            License license = licenseRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("License not found"));
            return licenseMapper.entityToDto(license);
        } catch (EntityNotFoundException e) {
            log.error("Error getting license with id: {}. Error:{}", id, e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void update(LicenseDTO licenseDTO) {
        try {
            licenseRepository.findById(licenseDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("License not found"));
            licenseRepository.save(licenseMapper.dtoToEntity(licenseDTO));
        } catch (EntityNotFoundException e) {
            log.error("Error updating license with id: {}. Error:{}", licenseDTO.getId(), e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            licenseRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("License not found"));
            licenseRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error("Error deleting license with id: {}. Error:{}", id, e.getMessage());
            throw e;
        }
    }

}
