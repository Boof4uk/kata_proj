package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.mapper.AtmMapper;
import com.bank.publicinfo.repository.AtmRepository;
import com.bank.publicinfo.service.AtmService;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AtmServiceImpl implements AtmService {
    
    private final AtmMapper atmMapper;
    private final AtmRepository atmRepository;

    @Transactional(readOnly = true)
    public List<AtmDTO> getAll() {
        return atmRepository.findAll().stream().map(atmMapper::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    public void save(AtmDTO atmDTO) {
        atmRepository.save(atmMapper.dtoToEntity(atmDTO));
    }

    @Transactional(readOnly = true)
    public AtmDTO find(Long id) {
        try {
            Atm atm = atmRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Atm not found"));
            return atmMapper.entityToDto(atm);
        } catch (EntityNotFoundException e) {
            log.error("Error getting atm with id: {}. Error:{}", id, e.getMessage());
            throw e;
        }
    }
        @Transactional
    public void update(AtmDTO atmDTO) {
        try {
            atmRepository.findById(atmDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Atm not found"));
            atmRepository.save(atmMapper.dtoToEntity(atmDTO));
        } catch (EntityNotFoundException e) {
            log.error("Error updating atm with id: {}. Error:{}", atmDTO.getId(), e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            atmRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Atm not found"));
            atmRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error("Error deleting atm with id: {}. Error:{}", id, e.getMessage());
            throw e;
        }
    }
}
