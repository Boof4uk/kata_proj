package com.bank.account.service;

import com.bank.account.dto.AccountDetailsDTO;

import java.util.List;

public interface AccountDetailsService {

    void create(AccountDetailsDTO accountDetailsDTO);

    AccountDetailsDTO findAccountDetailsById(Long id);

    void delete (Long id);

    void update (AccountDetailsDTO accountDetailsDTO);

    List<AccountDetailsDTO> getAccountDetailsList();
}
