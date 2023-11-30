package com.bank.account.repository;

import com.bank.account.entity.AccountAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AccountAuditRepository extends JpaRepository<AccountAudit,Long> {
}
