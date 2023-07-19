package com.example.devsu.exam.application.infrastructure.domain.request;

import com.example.devsu.exam.application.utlils.enums.AccountType;
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
public class AccountDtoRequest {

  private String accountNumber;
  private AccountType accountType;
  private int initialBalance;
}
