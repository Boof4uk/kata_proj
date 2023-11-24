package com.bank.transfer.repository;

import com.bank.transfer.entity.PhoneTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для сущности PhoneTransfer
 */

@Repository
public interface PhoneTransferRepository extends JpaRepository<PhoneTransfer, Long> {
}
