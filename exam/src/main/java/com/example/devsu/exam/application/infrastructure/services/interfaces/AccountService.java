package com.example.devsu.exam.application.infrastructure.services.interfaces;

import com.example.devsu.exam.application.infrastructure.domain.request.AccountDtoRequest;
import com.example.devsu.exam.application.infrastructure.domain.response.AccountDtoResponse;

import java.util.List;
import java.util.Optional;

public interface AccountService {

  Optional<List<AccountDtoResponse>> getAllAccounts();

  Optional<AccountDtoResponse> getAccount(Long accountId);

  void createAccount(Long clientId, AccountDtoRequest request);

  void updateAccount(Long accountId, AccountDtoRequest request);

  void deleteAccount(Long accountId);
}
