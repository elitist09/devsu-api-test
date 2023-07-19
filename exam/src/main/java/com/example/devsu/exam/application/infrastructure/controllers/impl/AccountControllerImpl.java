package com.example.devsu.exam.application.infrastructure.controllers.impl;

import com.example.devsu.exam.application.infrastructure.controllers.interfaces.AccountController;
import com.example.devsu.exam.application.infrastructure.domain.request.AccountDtoRequest;
import com.example.devsu.exam.application.infrastructure.domain.response.AccountDtoResponse;
import com.example.devsu.exam.application.infrastructure.services.interfaces.AccountService;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {

  private final AccountService service;
  Logger logger = LoggerFactory.getLogger(TransactionControllerImpl.class);


  @Override
  @GetMapping("/all")
  public ResponseEntity<List<AccountDtoResponse>> getAllAccounts() {

    logger.info("Retrieving all " +
            "accounts");

    Optional<List<AccountDtoResponse>> optionalAccounts = service.getAllAccounts();

    if(optionalAccounts.isPresent()){

      logger.info("Retrieving completed");
      return new ResponseEntity<>(optionalAccounts.get(), HttpStatus.OK);
    }

    logger.error("Error while retrieving account list");

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

  }

  @Override
  @GetMapping("accountId/{accountId}")
  public ResponseEntity<AccountDtoResponse> getAccount(@PathVariable Long accountId) {

    logger.info("Retrieving account");

    Optional<AccountDtoResponse> optionalAccount = service.getAccount(accountId);
    if(optionalAccount.isPresent()){

      logger.info("Retrieving completed");

      return new ResponseEntity<>(optionalAccount.get(), HttpStatus.OK);
    }

    logger.error("Error while retrieving account information");

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @PostMapping("/create/{clientId}")
  public ResponseEntity createAccount(
      @PathVariable Long clientId, @Valid @RequestBody AccountDtoRequest request) {

    logger.info("creating new transaction");

    service.createAccount(clientId, request);

    logger.info("creation completed");

    return new ResponseEntity(HttpStatus.CREATED);
  }

  @Override
  @PutMapping("/update/{accountId}")
  public ResponseEntity updateAccount(
      @PathVariable Long accountId, @Valid @RequestBody AccountDtoRequest request) {

    logger.info("updating transaction");

    service.updateAccount(accountId, request);

    logger.info("transaction completed");

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @Override
  @DeleteMapping("/delete/{accountId}")
  public ResponseEntity deleteAccount(@PathVariable Long accountId) {

    logger.info("deleting transaction");

    service.deleteAccount(accountId);

    logger.info("deletion completed");

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
