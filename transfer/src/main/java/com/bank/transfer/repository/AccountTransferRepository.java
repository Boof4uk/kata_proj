package com.bank.transfer.repository;

import com.bank.transfer.entity.AccountTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для сущности AccountTransfer
 */

@Repository
public interface AccountTransferRepository extends JpaRepository<AccountTransfer, Long> {
}
