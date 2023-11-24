package com.bank.antifraud.repository;

import com.bank.antifraud.entity.AntifraudAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для сущности AntifraudAudit
 */
@Repository
public interface AntifraudAuditRepository extends JpaRepository<AntifraudAudit, Long> {
}
