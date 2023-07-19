package com.example.devsu.exam.application.infrastructure.services.impl;

import com.example.devsu.exam.application.entities.AccountEntity;
import com.example.devsu.exam.application.entities.ClientEntity;
import com.example.devsu.exam.application.infrastructure.domain.request.AccountDtoRequest;
import com.example.devsu.exam.application.infrastructure.domain.response.AccountDtoResponse;
import com.example.devsu.exam.application.infrastructure.domain.response.TransactionDtoResponse;
import com.example.devsu.exam.application.infrastructure.repositories.AccountRepository;
import com.example.devsu.exam.application.infrastructure.repositories.ClientRepository;
import com.example.devsu.exam.application.infrastructure.services.interfaces.AccountService;
import com.example.devsu.exam.application.utlils.mapper.AccountMapper;
import com.example.devsu.exam.application.utlils.mapper.TransactionMapper;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
  private final AccountRepository accountRepository;
  private final ClientRepository clientRepository;
  private final AccountMapper accountMapper;
  private final TransactionMapper transactionMapper;

  @Override
  public Optional<List<AccountDtoResponse>> getAllAccounts() {
    Optional<List<AccountEntity>> optionalAccountList = Optional.of(accountRepository.findAll());

    if(optionalAccountList.isPresent()){

      return Optional.ofNullable(accountMapper.mapAllToResponseDto(optionalAccountList.get()));
    }
    return Optional.empty();
  }

  @Override
  public Optional<AccountDtoResponse> getAccount(Long accountId) {
    Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(accountId);

    if (optionalAccountEntity.isPresent()) {
      AccountEntity accountEntity = optionalAccountEntity.get();
      List<TransactionDtoResponse> transactionList =
          transactionMapper.mapAllToResponseDto(accountEntity.getTransactions());
      return Optional.ofNullable(accountMapper.mapToResponseDto(accountEntity, transactionList));
    }

    return Optional.empty();
  }

  @Override
  public void createAccount(Long clientId, AccountDtoRequest request) {
    Optional<ClientEntity> optionalClientEntity = clientRepository.findById(clientId);

    if (optionalClientEntity.isPresent()) {
      ClientEntity clientEntity = optionalClientEntity.get();
      AccountEntity accountEntity = accountMapper.mapToEntity(clientEntity, request);
      clientEntity.getAccounts().add(accountEntity);
      accountEntity.setClientEntity(clientEntity);

      accountRepository.save(accountEntity);
      clientRepository.save(clientEntity);

    }

  }

  @Override
  public void updateAccount(Long accountId, AccountDtoRequest request) {
    Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(accountId);
    if (optionalAccountEntity.isPresent()) {

      AccountEntity accountEntity = optionalAccountEntity.get();
      accountEntity.setAccountNumber(request.getAccountNumber());
      accountEntity.setAccountType(request.getAccountType());

      accountRepository.save(accountEntity);
    }

  }

  @Override
  public void deleteAccount(Long accountId) {
    accountRepository.deleteById(accountId);
  }
}
