package com.example.devsu.exam.application.infrastructure.controllers.interfaces;

import com.example.devsu.exam.application.infrastructure.domain.request.TransactionDtoRequest;
import com.example.devsu.exam.application.infrastructure.domain.response.TransactionDtoResponse;
import com.example.devsu.exam.application.infrastructure.domain.response.TransactionReportElement;
import com.example.devsu.exam.application.utlils.exception.WithdrawException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface TransactionController {

  ResponseEntity<List<TransactionDtoResponse>> getAllTransactions();
  ResponseEntity<TransactionDtoResponse> getTransaction(Long transactionId);

  ResponseEntity createTransaction(Long accountId, TransactionDtoRequest request)
      throws WithdrawException;

  ResponseEntity updateTransaction(Long transactionId, TransactionDtoRequest request);

  ResponseEntity deleteTransaction(Long transactionId);

  ResponseEntity<List<TransactionReportElement>> getReportByDateRangeAndUser(
      LocalDate startDate, LocalDate endDate, String clientId);
}
