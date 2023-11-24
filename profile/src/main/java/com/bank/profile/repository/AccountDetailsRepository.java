package com.bank.profile.repository;

import com.bank.profile.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AccountDetailsRepository extends JpaRepository<AccountDetails,Long> {
    Set<AccountDetails> findByProfileId(Long profileId);
}
