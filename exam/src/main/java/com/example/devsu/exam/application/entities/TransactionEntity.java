package com.example.devsu.exam.application.entities;

import com.example.devsu.exam.application.utlils.enums.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
@Entity(name = "TRANSACTIONS")
@Table(name = "TRANSACTIONS")
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "date cannot be null")
  private LocalDateTime date;
  private TransactionType transactionType;
  private int amount;
  private int balance;
  @ManyToOne() @JoinColumn() private AccountEntity account;
}
