package com.bank.antifraud.repository;

import com.bank.antifraud.entity.SuspiciousAccountTransfers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для сущности SuspiciousAccountTransfers
 */
@Repository
public interface SuspiciousAccountTransfersRepository extends JpaRepository <SuspiciousAccountTransfers,Long > {
}
