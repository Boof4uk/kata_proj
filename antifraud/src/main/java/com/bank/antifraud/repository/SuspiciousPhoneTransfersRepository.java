package com.bank.antifraud.repository;

import com.bank.antifraud.entity.SuspiciousPhoneTransfers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий для сущности SuspiciousPhoneTransfers
 */
@Repository
public interface SuspiciousPhoneTransfersRepository extends JpaRepository<SuspiciousPhoneTransfers, Long> {
}
