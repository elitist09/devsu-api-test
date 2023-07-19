package com.example.devsu.exam.application.utlils.mapper;

import com.example.devsu.exam.application.entities.AccountEntity;
import com.example.devsu.exam.application.entities.PersonEntity;
import com.example.devsu.exam.application.infrastructure.domain.request.AccountDtoRequest;
import com.example.devsu.exam.application.infrastructure.domain.response.AccountDtoResponse;
import com.example.devsu.exam.application.infrastructure.domain.response.TransactionDtoResponse;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {

  private final TransactionMapper transactionMapper;

  public AccountEntity mapToEntity(PersonEntity personEntity, AccountDtoRequest request) {
    return AccountEntity.builder()
        .clientId(personEntity.getId())
        .accountNumber(request.getAccountNumber())
        .initialBalance(request.getInitialBalance())
        .accountType(request.getAccountType())
        .build();
  }

  public AccountDtoResponse mapToResponseDto(
      AccountEntity accountEntity, List<TransactionDtoResponse> transactionList) {
    return AccountDtoResponse.builder()
        .accountNumber(accountEntity.getAccountNumber())
        .accountType(accountEntity.getAccountType())
        .initialBalance(accountEntity.getInitialBalance())
        .transactions(transactionList)
        .build();
  }

  public List<AccountDtoRequest> mapAllToRequestDto(List<AccountEntity> accountEntityList) {
    List<AccountDtoRequest> requestList = new ArrayList<>();
    for (AccountEntity entity : accountEntityList) {
      AccountDtoRequest request =
          AccountDtoRequest.builder()
              .accountNumber(entity.getAccountNumber())
              .accountType(entity.getAccountType())
              .initialBalance(entity.getInitialBalance())
              .build();

      requestList.add(request);
    }

    return requestList;
  }

  public List<AccountDtoResponse> mapAllToResponseDto(List<AccountEntity> accountEntityList) {
    List<AccountDtoResponse> requestList = new ArrayList<>();
    for (AccountEntity entity : accountEntityList) {
      AccountDtoResponse request =
              AccountDtoResponse.builder()
                      .accountNumber(entity.getAccountNumber())
                      .accountType(entity.getAccountType())
                      .initialBalance(entity.getInitialBalance())
                      .transactions(transactionMapper.mapAllToResponseDto(entity.getTransactions()))
                      .build();

      requestList.add(request);
    }

    return requestList;
  }
}
