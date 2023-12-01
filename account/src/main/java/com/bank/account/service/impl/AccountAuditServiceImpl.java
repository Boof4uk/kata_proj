package com.bank.account.service.impl;

import com.bank.account.dto.AccountAuditDTO;
import com.bank.account.entity.AccountAudit;
import com.bank.account.mapper.AccountAuditMapper;
import com.bank.account.repository.AccountAuditRepository;
import com.bank.account.service.AccountAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountAuditServiceImpl implements AccountAuditService {

    private final AccountAuditRepository accountAuditRepository;

    private final AccountAuditMapper mapper;


    @Override
    public void create(AccountAuditDTO accountAuditDTO) {
        mapper.toDTO(accountAuditRepository.save(mapper.toEntity(accountAuditDTO)));
    }

    @Override
    public AccountAuditDTO findAccountAuditToId(Long id) {
       AccountAudit accountAudit = accountAuditRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AccountAudit not found for id: " + id));
       return mapper.toDTO(accountAudit);
    }

    @Override
    public void delete(Long id) {
        accountAuditRepository.deleteById(id);
    }

    @Override
    public void update(AccountAuditDTO accountAuditDTO) {
        mapper.toDTO(accountAuditRepository.save(mapper.toEntity(accountAuditDTO)));
    }

    @Override
    public List<AccountAuditDTO> getAccountAuditList() {
        return mapper.toDTOList(accountAuditRepository.findAll());
    }
}
