package com.example.devsu.exam.application.infrastructure.controllers.interfaces;

import com.example.devsu.exam.application.infrastructure.domain.ClientDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ClientController {

  ResponseEntity<List<ClientDto>> getAllClients();

  ResponseEntity createClient(ClientDto clientDto);

  ResponseEntity<ClientDto> getClientById(Long clientId);

  ResponseEntity updateClient(Long clientId, ClientDto clientDto);

  ResponseEntity deleteClient(Long clientId);
}
