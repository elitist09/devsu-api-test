package com.example.devsu.exam.application.infrastructure.domain.response;

import com.example.devsu.exam.application.utlils.enums.AccountType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReportElement {

  private LocalDateTime date;
  private String clientName;
  private String accountNumber;
  private AccountType accountType;
  private int previousAmount;
  private boolean status;
  private int amount;
  private int balance;
}
