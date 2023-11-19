package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.mapper.BankDetailsMapper;
import com.bank.publicinfo.repository.BankDetailsRepository;
import com.bank.publicinfo.service.BankDetailsService;
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
public class BankDetailsServiceImpl implements BankDetailsService {

    private final BankDetailsMapper bankDetailsMapper;
    private final BankDetailsRepository bankDetailsRepository;

    @Transactional(readOnly = true)
    public List<BankDetailsDTO> getAll() {
        return bankDetailsRepository.findAll().stream().map(bankDetailsMapper::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    public void save(BankDetailsDTO bankDetailsDTO) {
        bankDetailsRepository.save(bankDetailsMapper.dtoToEntity(bankDetailsDTO));
    }

    @Transactional(readOnly = true)
    public BankDetailsDTO find(Long id) {
        try {
            BankDetails bankDetails = bankDetailsRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("BankDetails not found"));
            return bankDetailsMapper.entityToDto(bankDetails);
        } catch (EntityNotFoundException e) {
            log.error("Error getting bankDetails with id: {}. Error:{}", id, e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void update(BankDetailsDTO bankDetailsDTO) {
        try {
            bankDetailsRepository.findById(bankDetailsDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("BankDetails not found"));
            bankDetailsRepository.save(bankDetailsMapper.dtoToEntity(bankDetailsDTO));
        } catch (EntityNotFoundException e) {
            log.error("Error updating bankDetails with id: {}. Error:{}", bankDetailsDTO.getId(), e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            bankDetailsRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("BankDetails not found"));
            bankDetailsRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error("Error deleting bankDetails with id: {}. Error:{}", id, e.getMessage());
            throw e;
        }
    }
}
