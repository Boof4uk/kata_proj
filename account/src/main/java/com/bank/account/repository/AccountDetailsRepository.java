package com.bank.account.repository;

import com.bank.account.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {
}
