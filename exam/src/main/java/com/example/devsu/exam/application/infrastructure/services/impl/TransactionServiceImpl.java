package com.example.devsu.exam.application.infrastructure.services.impl;

import com.example.devsu.exam.application.entities.AccountEntity;
import com.example.devsu.exam.application.entities.ClientEntity;
import com.example.devsu.exam.application.entities.TransactionEntity;
import com.example.devsu.exam.application.infrastructure.domain.request.TransactionDtoRequest;
import com.example.devsu.exam.application.infrastructure.domain.response.TransactionDtoResponse;
import com.example.devsu.exam.application.infrastructure.domain.response.TransactionReportElement;
import com.example.devsu.exam.application.infrastructure.repositories.AccountRepository;
import com.example.devsu.exam.application.infrastructure.repositories.ClientRepository;
import com.example.devsu.exam.application.infrastructure.repositories.TransactionRepository;
import com.example.devsu.exam.application.infrastructure.services.interfaces.TransactionService;
import com.example.devsu.exam.application.utlils.enums.TransactionType;
import com.example.devsu.exam.application.utlils.exception.WithdrawException;
import com.example.devsu.exam.application.utlils.helper.WithdrawalHelper;
import com.example.devsu.exam.application.utlils.mapper.TransactionMapper;
import com.example.devsu.exam.application.utlils.mapper.TransactionHistoryElementMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
  private final TransactionMapper transactionMapper;
  private final AccountRepository accountRepository;
  private final TransactionRepository transactionRepository;
  private final ClientRepository clientRepository;
  private final TransactionHistoryElementMapper transactionReportElementMapper;
  private final WithdrawalHelper withdrawalHelper;

  @Override
  public Optional<List<TransactionDtoResponse>> getAllTransactions() {

    Optional<List<TransactionEntity>> optionalTransactions = Optional.of(transactionRepository.findAll());
    if(optionalTransactions.isPresent()){
      return Optional.ofNullable(transactionMapper.mapAllToResponseDto(optionalTransactions.get()));
    }

    return Optional.empty();
  }

  @Override
  public Optional<TransactionDtoResponse> getTransaction(Long transactionId) {

    Optional<TransactionEntity> transactionEntity = transactionRepository.findById(transactionId);
    return transactionEntity.map(transactionMapper::mapToResponseDto);

  }

  @Override
  public void createTransaction(Long accountId, TransactionDtoRequest request)
      throws WithdrawException {
    Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(accountId);

    if (optionalAccountEntity.isPresent()
            && withdrawalHelper.isAmountZero(request)
        && withdrawalHelper.isOverWithdrawLimit(
            request, optionalAccountEntity.get().getTransactions())
        && withdrawalHelper.isEnoughMoney(request.getAmount(), optionalAccountEntity.get())) {

      AccountEntity accountEntity = optionalAccountEntity.get();
      TransactionEntity transactionEntity = transactionMapper.mapToEntity(request, accountEntity);

      accountEntity.setInitialBalance(transactionEntity.getBalance());
      accountEntity.getTransactions().add(transactionEntity);
      transactionEntity.setAccount(accountEntity);

      accountRepository.save(accountEntity);
    }
  }

  @Override
  public void updateTransaction(Long transactionId, TransactionDtoRequest request) {
    Optional<TransactionEntity> optionalTransactionEntity =
        transactionRepository.findById(transactionId);

    if (optionalTransactionEntity.isPresent()) {
      TransactionEntity transactionEntity = optionalTransactionEntity.get();

      transactionEntity.setTransactionType(TransactionType.valueOf(request.getTransactionType()));
      transactionEntity.setDate(request.getTransactionDate());
      transactionEntity.setAmount(request.getAmount());
      transactionRepository.save(transactionEntity);
    }

  }

  @Override
  public void deleteTransaction(Long transactionId) {

    transactionRepository.deleteById(transactionId);
  }

  @Override
  public Optional<List<TransactionReportElement>> getReportByDateRangeAndUser(
      LocalDate startDate, LocalDate endDate, String clientId) {

    Optional<ClientEntity> optionalClientEntity =
        Optional.ofNullable(clientRepository.findByClientId(clientId));

    if (optionalClientEntity.isPresent()) {
      return Optional.ofNullable(
          transactionReportElementMapper.getReport(optionalClientEntity.get(), startDate, endDate));
    }

    return Optional.empty();
  }
}
