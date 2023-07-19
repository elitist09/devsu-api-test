package com.example.devsu.exam.application.infrastructure.controllers.impl;

import com.example.devsu.exam.application.infrastructure.controllers.interfaces.TransactionController;
import com.example.devsu.exam.application.infrastructure.domain.request.TransactionDtoRequest;
import com.example.devsu.exam.application.infrastructure.domain.response.TransactionDtoResponse;
import com.example.devsu.exam.application.infrastructure.domain.response.TransactionReportElement;
import com.example.devsu.exam.application.infrastructure.services.interfaces.TransactionService;
import com.example.devsu.exam.application.utlils.exception.WithdrawException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionControllerImpl implements TransactionController {

  private final TransactionService transactionService;
  Logger logger = LoggerFactory.getLogger(TransactionControllerImpl.class);

  @Override
  @GetMapping("/all")
  public ResponseEntity<List<TransactionDtoResponse>> getAllTransactions() {
    logger.info("Retrieving all " +
            "transactions");

    Optional<List<TransactionDtoResponse>> optionalTransactionList = transactionService.getAllTransactions();

    if(optionalTransactionList.isPresent()){
      logger.info("Retrieving completed");

      new ResponseEntity<>(optionalTransactionList.get(), HttpStatus.OK);
    }
    logger.error("Error while retrieving transaction list");

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @GetMapping("/transactionId/{transactionId}")
  public ResponseEntity<TransactionDtoResponse> getTransaction(@PathVariable Long transactionId) {

    logger.info("Retrieving transaction");

    Optional<TransactionDtoResponse> optionalTransactionDtoResponse =
        transactionService.getTransaction(transactionId);


    if(optionalTransactionDtoResponse.isPresent()){
      logger.info("Retrieving completed");

      return new ResponseEntity<>(optionalTransactionDtoResponse.get(), HttpStatus.OK);
    }

    logger.error("Error while retrieving transaction information");

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Override
  @PostMapping("/create/{accountId}")
  public ResponseEntity createTransaction(
      @PathVariable Long accountId, @RequestBody TransactionDtoRequest request)
      throws WithdrawException {

    logger.info("creating new transaction");

    transactionService.createTransaction(accountId, request);
    logger.info("creation completed");

    return new ResponseEntity(HttpStatus.CREATED);
  }

  @Override
  @PutMapping("/update/{transactionId}")
  public ResponseEntity updateTransaction(
      @PathVariable Long transactionId, @RequestBody TransactionDtoRequest request) {

    logger.info("updating transaction");

    transactionService.updateTransaction(transactionId, request);

    logger.info("transaction completed");


    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @Override
  @DeleteMapping("/delete/{transactionId}")
  public ResponseEntity deleteTransaction(@PathVariable Long transactionId) {

    logger.info("deleting transaction");

    transactionService.deleteTransaction(transactionId);

    logger.info("deletion completed");

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @Override
  @GetMapping("/report")
  public ResponseEntity<List<TransactionReportElement>> getReportByDateRangeAndUser(
      @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
      @RequestParam String clientId) {

    logger.info("Creating report");

    Optional<List<TransactionReportElement>> optionalReport =
        transactionService.getReportByDateRangeAndUser(startDate, endDate, clientId);

    if(optionalReport.isPresent()){
      logger.info("Report created");
      return new ResponseEntity<>(optionalReport.get(), HttpStatus.OK);
    }

    logger.error("Error while creating report");
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
