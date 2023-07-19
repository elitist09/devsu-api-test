package com.example.devsu.exam.application.infrastructure.controllers.interfaces;

import com.example.devsu.exam.application.infrastructure.domain.request.AccountDtoRequest;
import com.example.devsu.exam.application.infrastructure.domain.response.AccountDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountController {

  ResponseEntity<List<AccountDtoResponse>> getAllAccounts();
  ResponseEntity<AccountDtoResponse> getAccount(Long accountId);

  ResponseEntity createAccount(Long clientId, AccountDtoRequest request);

  ResponseEntity updateAccount(Long accountId, AccountDtoRequest request);

  ResponseEntity deleteAccount(Long accountId);
}
