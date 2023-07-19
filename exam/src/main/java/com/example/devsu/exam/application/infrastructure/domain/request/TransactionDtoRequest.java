package com.example.devsu.exam.application.infrastructure.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDtoRequest {

    private LocalDateTime transactionDate;
    private String transactionType;
    private int amount; // monto a depositar o retirar

}
