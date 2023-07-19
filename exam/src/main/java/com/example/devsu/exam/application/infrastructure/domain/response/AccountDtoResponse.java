package com.example.devsu.exam.application.infrastructure.domain.response;

import com.example.devsu.exam.application.utlils.enums.AccountType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDtoResponse {

  private String accountNumber;
  private AccountType accountType;
  private int initialBalance;
  private List<TransactionDtoResponse> transactions;
}
