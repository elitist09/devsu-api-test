package com.example.devsu.exam.application.infrastructure.services.impl;

import com.example.devsu.exam.application.entities.ClientEntity;
import com.example.devsu.exam.application.infrastructure.domain.ClientDto;
import com.example.devsu.exam.application.infrastructure.repositories.ClientRepository;
import com.example.devsu.exam.application.infrastructure.services.interfaces.ClientService;
import com.example.devsu.exam.application.utlils.mapper.ClientMapper;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

  private final ClientRepository repository;
  private final ClientMapper clientMapper;

  @Override
  public Optional<List<ClientDto>> getAllClients() {
    Optional<List<ClientEntity>> clientEntities = Optional.of(repository.findAll());
    if(clientEntities.isPresent()){
      return Optional.ofNullable(clientMapper.mapAllClientsToDto(clientEntities.get()));
    }
    return Optional.empty();

  }

  public Optional<ClientDto> getClientById(Long clientId) {
    Optional<ClientEntity> optionalClientEntity = repository.findById(clientId);

    if (optionalClientEntity.isPresent()) {
      return Optional.ofNullable(clientMapper.mapToDto(optionalClientEntity.get()));
    }

    return Optional.empty();
  }

  public boolean createClient(ClientDto clientDto) {

    if (clientDto != null) {
      repository.save(clientMapper.mapToEntity(clientDto));
      return true;
    }

    return false;
  }

  @Override
  public boolean updateClient(Long clientId, ClientDto clientDto) {
    Optional<ClientEntity> optionalClientEntity = repository.findById(clientId);
    if (optionalClientEntity.isPresent()) {
      ClientEntity clientEntity = optionalClientEntity.get();

      clientEntity.setName(clientDto.getName());
      clientEntity.setGender(clientDto.getGender());
      clientEntity.setAge(clientDto.getAge());
      clientEntity.setIdNumber(clientDto.getIdNumber());
      clientEntity.setAddress(clientDto.getAddress());
      clientEntity.setPhoneNumber(clientDto.getPhoneNumber());
      clientEntity.setClientId(clientDto.getClientId());
      clientEntity.setPassword(clientDto.getPassword());
      clientEntity.setStatus(clientDto.isStatus());

      repository.save(clientEntity);
      return true;
    }

    return false;
  }

  @Override
  public void deleteClient(Long clientId) {
    repository.deleteById(clientId);
  }
}
