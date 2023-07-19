package com.example.devsu.exam.application.utlils.mapper;

import com.example.devsu.exam.application.entities.ClientEntity;
import com.example.devsu.exam.application.infrastructure.domain.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientMapper {

  private final AccountMapper accountMapper;

  public ClientEntity mapToEntity(ClientDto clientDto) {
    ClientEntity clientEntity = new ClientEntity();
    clientEntity.setName(clientDto.getName());
    clientEntity.setGender(clientDto.getGender());
    clientEntity.setAge(clientDto.getAge());
    clientEntity.setAddress(clientDto.getAddress());
    clientEntity.setIdNumber(clientDto.getIdNumber());
    clientEntity.setPhoneNumber(clientDto.getPhoneNumber());
    clientEntity.setClientId(clientDto.getClientId());
    clientEntity.setPassword(clientDto.getPassword());
    clientEntity.setStatus(clientDto.isStatus());

    return clientEntity;
  }

  public ClientDto mapToDto(ClientEntity clientEntity) {

    return ClientDto.builder()
        .name(clientEntity.getName())
        .gender(clientEntity.getGender())
        .age(clientEntity.getAge())
        .idNumber(clientEntity.getIdNumber())
        .address(clientEntity.getAddress())
        .phoneNumber(clientEntity.getPhoneNumber())
        .clientId(clientEntity.getClientId())
        .password(clientEntity.getPassword())
        .status(clientEntity.isStatus())
        .accounts(accountMapper.mapAllToRequestDto(clientEntity.getAccounts()))
        .build();
  }

  public List<ClientDto> mapAllClientsToDto(List<ClientEntity> clients) {
    List<ClientDto> clientDtoList = new ArrayList<>();
    for (ClientEntity client :
            clients) {
      clientDtoList.add(mapToDto(client));
    }
    return clientDtoList;
  }

}
