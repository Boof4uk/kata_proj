package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.mapper.CertificateMapper;
import com.bank.publicinfo.repository.CertificateRepository;
import com.bank.publicinfo.service.CertificateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@AllArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateMapper certificateMapper;
    private final CertificateRepository certificateRepository;

    @Transactional(readOnly = true)
    public List<CertificateDTO> getAll() {
        return certificateRepository.findAll().stream().map(certificateMapper::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    public void save(CertificateDTO certificateDTO) {
        certificateRepository.save(certificateMapper.dtoToEntity(certificateDTO));
    }

    @Transactional(readOnly = true)
    public CertificateDTO find(Long id) {

        try {
            Certificate certificate = certificateRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Certificate not found"));
            return certificateMapper.entityToDto(certificate);
        } catch (EntityNotFoundException e) {
            log.error("Error getting certificate with id: {}. Error:{}", id, e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void update(CertificateDTO certificateDTO) {
        try {
            certificateRepository.findById(certificateDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Certificate not found"));
            certificateRepository.save(certificateMapper.dtoToEntity(certificateDTO));
        } catch (EntityNotFoundException e) {
            log.error("Error updating certificate with id: {}. Error:{}", certificateDTO.getId(), e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            certificateRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Certificate not found"));
            certificateRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error("Error deleting certificate with id: {}. Error:{}", id, e.getMessage());
            throw e;
        }
    }
}
