package com.example.devsu.exam.application.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.List;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CLIENTS")
@Builder
@Table(name = "CLIENTS")
public class ClientEntity extends PersonEntity {

  @NotNull(message = "clientId cannot be null")
  private String clientId;
  @NotNull(message = "Password cannot be null")
  private String password;
  private boolean status;

  @OneToMany(
      mappedBy = "clientEntity",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<AccountEntity> accounts;
}
