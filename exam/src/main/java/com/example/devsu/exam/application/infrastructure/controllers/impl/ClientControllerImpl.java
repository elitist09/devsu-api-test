package com.example.devsu.exam.application.infrastructure.controllers.impl;

import com.example.devsu.exam.application.infrastructure.controllers.interfaces.ClientController;
import com.example.devsu.exam.application.infrastructure.domain.ClientDto;
import com.example.devsu.exam.application.infrastructure.services.interfaces.ClientService;
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
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientController {

  private final ClientService clientService;
  Logger logger = LoggerFactory.getLogger(TransactionControllerImpl.class);

  @Override
  @GetMapping("/all")
  public ResponseEntity<List<ClientDto>> getAllClients() {
    logger.info("Retrieving all " +
            "transactions");

    Optional<List<ClientDto>> optionalClientList = clientService.getAllClients();

    if(optionalClientList.isPresent()){
      logger.info("Retrieving completed");

      return new ResponseEntity<>(optionalClientList.get(), HttpStatus.OK);
    }

    logger.error("Error while retrieving clients list");

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/clientId/{clientId}")
  public ResponseEntity<ClientDto> getClientById(@PathVariable Long clientId) {

    logger.info("Retrieving transaction");

    Optional<ClientDto> optionalClientDto = clientService.getClientById(clientId);


    if(optionalClientDto.isPresent()){
      logger.info("Retrieving completed");

      return new ResponseEntity<>(optionalClientDto.get(), HttpStatus.OK);
    }
    logger.error("Error while retrieving client information");

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/create")
  public ResponseEntity createClient(@Valid @RequestBody ClientDto clientDto) {
    logger.info("creating new client");

    clientService.createClient(clientDto);

    logger.info("creation completed");


    return new ResponseEntity(HttpStatus.CREATED);
  }

  @PutMapping("/update/{clientId}")
  public ResponseEntity updateClient(
      @PathVariable Long clientId, @Valid @RequestBody ClientDto clientDto) {

    logger.info("updating transaction");

    clientService.updateClient(clientId, clientDto);

    logger.info("transaction completed");

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @Override
  @DeleteMapping("/delete/{clientId}")
  public ResponseEntity deleteClient(@PathVariable Long clientId) {

    logger.info("deleting transaction");

    clientService.deleteClient(clientId);

    logger.info("deletion completed");

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
