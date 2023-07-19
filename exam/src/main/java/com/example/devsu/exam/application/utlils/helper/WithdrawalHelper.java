package com.example.devsu.exam.application.utlils.helper;

import com.example.devsu.exam.application.entities.AccountEntity;
import com.example.devsu.exam.application.entities.TransactionEntity;
import com.example.devsu.exam.application.infrastructure.domain.request.TransactionDtoRequest;
import com.example.devsu.exam.application.utlils.exception.WithdrawException;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class WithdrawalHelper {

  public static final int WITHDRAW_LIMIT = 1000;

  public boolean isEnoughMoney(int transactionAmount, AccountEntity entity)
      throws WithdrawException {

    if (transactionAmount * -1 > entity.getInitialBalance()) {
      throw new WithdrawException("Not enough money in account");
    }

    return true;
  }

  public boolean isOverWithdrawLimit(
          TransactionDtoRequest request, List<TransactionEntity> transactions)
      throws WithdrawException {
    if (request.getAmount() < -WITHDRAW_LIMIT) {
      throw new WithdrawException("Daily transaction limit exceeded");
    }

    if (request.getAmount() > 0) {
      return true;
    }

    List<TransactionEntity> dateTransactions =
        transactions.stream()
            .filter(
                transaction ->
                    transaction.getDate().isEqual(request.getTransactionDate())
                        && transaction.getAmount() < 0)
            .toList();

    int totalWithdrawn = request.getAmount();
    for (TransactionEntity transaction : dateTransactions) {
      totalWithdrawn += transaction.getAmount();
      if (totalWithdrawn < -WITHDRAW_LIMIT) {

        throw new WithdrawException("Daily transaction limit exceeded");
      }
    }
    return true;
  }

  public boolean isAmountZero(TransactionDtoRequest request) throws WithdrawException {
    if (request.getAmount() == 0) {
      throw new WithdrawException("Insert an amount");

    }
    return true;
  }
}
