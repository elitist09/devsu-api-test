package com.example.devsu.exam.application.infrastructure.services.interfaces;

import com.example.devsu.exam.application.infrastructure.domain.request.TransactionDtoRequest;
import com.example.devsu.exam.application.infrastructure.domain.response.TransactionDtoResponse;
import com.example.devsu.exam.application.infrastructure.domain.response.TransactionReportElement;
import com.example.devsu.exam.application.utlils.exception.WithdrawException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionService {

  Optional<List<TransactionDtoResponse>> getAllTransactions();

  Optional<TransactionDtoResponse> getTransaction(Long transactionId);

  void createTransaction(Long accountId, TransactionDtoRequest transactionDto)
      throws WithdrawException;

  void updateTransaction(Long transactionId, TransactionDtoRequest request);

  void deleteTransaction(Long transactionId);

  Optional<List<TransactionReportElement>> getReportByDateRangeAndUser(
      LocalDate startDate, LocalDate endDate, String clientId);
}
