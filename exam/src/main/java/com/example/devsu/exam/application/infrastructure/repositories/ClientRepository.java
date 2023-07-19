package com.example.devsu.exam.application.infrastructure.repositories;

import com.example.devsu.exam.application.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
  ClientEntity findByClientId(String clientId);
}
