package com.example.devsu.exam.application.infrastructure.repositories;

import com.example.devsu.exam.application.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByClientIdAndId(Long clientId, Long accountId);
}
