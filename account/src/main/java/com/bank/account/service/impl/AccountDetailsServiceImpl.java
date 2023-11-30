package com.bank.account.service.impl;

import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.entity.AccountDetails;
import com.bank.account.mapper.AccountDetailsMapper;
import com.bank.account.repository.AccountDetailsRepository;
import com.bank.account.service.AccountDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {

    private final AccountDetailsRepository accountDetailsRepository;

    private final AccountDetailsMapper mapper ;


    @Override
    public void create(AccountDetailsDTO accountDetailsDTO) {
        mapper.toDTO(accountDetailsRepository.save(mapper.toEntity(accountDetailsDTO)));
    }

    @Override
    public AccountDetailsDTO findAccountDetailsById(Long id) {
        AccountDetails accountDetails =accountDetailsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AccountDetails not found for id: " + id));
        return mapper.toDTO(accountDetails);
    }

    @Override
    public void delete(Long id) {
        accountDetailsRepository.deleteById(id);
    }

    @Override
    public void update(AccountDetailsDTO accountDetailsDTO) {
        mapper.toDTO(accountDetailsRepository.save(mapper.toEntity(accountDetailsDTO)));
    }

    @Override
    public List<AccountDetailsDTO> getAccountDetailsList() {
        return mapper.toDTOList(accountDetailsRepository.findAll());
    }
}
