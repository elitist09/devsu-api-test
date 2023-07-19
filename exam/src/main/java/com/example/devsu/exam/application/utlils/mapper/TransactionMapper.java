package com.example.devsu.exam.application.utlils.mapper;

import com.example.devsu.exam.application.entities.AccountEntity;
import com.example.devsu.exam.application.entities.TransactionEntity;
import com.example.devsu.exam.application.infrastructure.domain.request.TransactionDtoRequest;
import com.example.devsu.exam.application.infrastructure.domain.response.TransactionDtoResponse;
import com.example.devsu.exam.application.utlils.enums.TransactionType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

  public TransactionEntity mapToEntity(
          TransactionDtoRequest transactionDto, AccountEntity transactionEntity) {
    return TransactionEntity.builder()
        .date(transactionDto.getTransactionDate())
        .amount(transactionDto.getAmount())
        .transactionType(isDebitOrCredit(transactionDto.getAmount()))
        .balance(transactionDto.getAmount() + transactionEntity.getInitialBalance())
        .build();
  }

  public TransactionDtoResponse mapToResponseDto(TransactionEntity transactionEntity) {
    return TransactionDtoResponse.builder()
        .previousAmount(transactionEntity.getBalance() - transactionEntity.getAmount())
        .amount(transactionEntity.getAmount())
        .transactionType(transactionEntity.getTransactionType())
        .transactionDate(transactionEntity.getDate())
        .balance(transactionEntity.getBalance())
        .build();
  }

  public List<TransactionDtoResponse> mapAllToResponseDto(List<TransactionEntity> entities) {

    List<TransactionDtoResponse> responseList = new ArrayList<>();
    for (TransactionEntity entity : entities) {
      responseList.add(mapToResponseDto(entity));
    }

    return responseList;
  }

  private TransactionType isDebitOrCredit(int amount) {
    return amount < 0 ? TransactionType.CREDITO : TransactionType.DEBITO;
  }
}
