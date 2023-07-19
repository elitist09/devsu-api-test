package com.example.devsu.exam.application.infrastructure.services.interfaces;

import com.example.devsu.exam.application.infrastructure.domain.ClientDto;
import java.util.List;
import java.util.Optional;

public interface ClientService {
  boolean createClient(ClientDto clientDto);

  Optional<ClientDto> getClientById(Long clientId);

  boolean updateClient(Long clientId, ClientDto clientDto);

  void deleteClient(Long clientId);

  Optional<List<ClientDto>> getAllClients();
}
