package com.example.devsu.exam.application.infrastructure.domain;

import com.example.devsu.exam.application.infrastructure.domain.request.AccountDtoRequest;
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
public class ClientDto {

  private String name;
  private char gender;
  private int age;
  private String idNumber;
  private String address;
  private String phoneNumber;
  private String clientId;
  private String password;
  private boolean status;
  private List<AccountDtoRequest> accounts;
}
