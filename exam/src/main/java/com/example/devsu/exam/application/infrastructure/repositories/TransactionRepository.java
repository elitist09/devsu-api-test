package com.example.devsu.exam.application.infrastructure.repositories;

import com.example.devsu.exam.application.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {}
