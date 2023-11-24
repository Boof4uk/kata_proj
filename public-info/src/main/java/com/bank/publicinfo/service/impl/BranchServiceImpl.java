package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.mapper.BranchMapper;
import com.bank.publicinfo.repository.BranchRepository;
import com.bank.publicinfo.service.BranchService;
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
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    @Transactional(readOnly = true)
    public List<BranchDTO> getAll() {
        return branchRepository.findAll().stream().map(branchMapper::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    public void save(BranchDTO branchDto) {
        branchRepository.save(branchMapper.dtoToEntity(branchDto));
    }

    @Transactional(readOnly = true)
    public BranchDTO find(Long id) {
        try {
            Branch branch = branchRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
            return branchMapper.entityToDto(branch);
        } catch (EntityNotFoundException e) {
            log.error("Error getting branch with id: {}. Error:{}", id, e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void update(BranchDTO branchDto) {
        try {
            branchRepository.findById(branchDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
            branchRepository.save(branchMapper.dtoToEntity(branchDto));
        } catch (EntityNotFoundException e) {
            log.error("Error updating branch with id: {}. Error:{}", branchDto.getId(), e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            branchRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
            branchRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.error("Error deleting branch with id: {}. Error:{}", id, e.getMessage());
            throw e;
        }
    }
}
