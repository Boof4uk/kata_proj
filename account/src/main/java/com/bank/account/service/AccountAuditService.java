package com.bank.account.service;

import com.bank.account.dto.AccountAuditDTO;

import java.util.List;

public interface AccountAuditService {

    void create(AccountAuditDTO accountAuditDTO);

    AccountAuditDTO findAccountAuditToId(Long id);

    void delete (Long id);

    void update (AccountAuditDTO accountAuditDTO);

    List<AccountAuditDTO> getAccountAuditList();
}
