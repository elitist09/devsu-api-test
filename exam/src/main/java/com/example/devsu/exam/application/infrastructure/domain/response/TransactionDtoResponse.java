package com.example.devsu.exam.application.infrastructure.domain.response;

import com.example.devsu.exam.application.utlils.enums.TransactionType;
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
public class TransactionDtoResponse {

  private LocalDateTime transactionDate;
  private TransactionType transactionType;
  private int previousAmount;
  private int amount;
  private int balance;
}
