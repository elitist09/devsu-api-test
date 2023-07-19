package com.example.devsu.exam.application.utlils.mapper;

import com.example.devsu.exam.application.entities.AccountEntity;
import com.example.devsu.exam.application.entities.ClientEntity;
import com.example.devsu.exam.application.entities.TransactionEntity;
import com.example.devsu.exam.application.infrastructure.domain.response.TransactionReportElement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TransactionHistoryElementMapper {

  public List<TransactionReportElement> getReport(
      ClientEntity clientEntity, LocalDate startDate, LocalDate endDate) {
    List<AccountEntity> accounts = clientEntity.getAccounts();
    List<TransactionReportElement> report = new ArrayList<>();

    for (AccountEntity account : accounts) {
      List<TransactionEntity> transactions = account.getTransactions();

      for (TransactionEntity transaction : transactions) {

        if (isBetweenDates(transaction, startDate, endDate)) {
          TransactionReportElement element =
              TransactionReportElement.builder()
                  .date(transaction.getDate())
                  .clientName(clientEntity.getName())
                  .accountNumber(account.getAccountNumber())
                  .accountType(account.getAccountType())
                  .previousAmount(transaction.getBalance() - transaction.getAmount())
                  .status(clientEntity.isStatus())
                  .amount(transaction.getAmount())
                  .balance(transaction.getBalance())
                  .build();

          report.add(element);
        }
      }
    }

    return report;
  }

  private boolean isBetweenDates(
      TransactionEntity transaction, LocalDate startDate, LocalDate endDate) {
    return transaction.getDate().toLocalDate().isAfter(startDate.minusDays(1))
        && transaction.getDate().toLocalDate().isBefore(endDate.plusDays(1));
  }
}
