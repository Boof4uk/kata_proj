package com.bank.transfer.repository;

import com.bank.transfer.entity.TransferAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для сущности TransferAudit
 */

@Repository
public interface TransferAuditRepository extends JpaRepository<TransferAudit, Long>  {
}
