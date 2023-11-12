package com.bank.transfer.repository;

import com.bank.transfer.entity.CardTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для сущности CardTransfer
 */

@Repository
public interface CardTransferRepository extends JpaRepository<CardTransfer, Long> {
}
