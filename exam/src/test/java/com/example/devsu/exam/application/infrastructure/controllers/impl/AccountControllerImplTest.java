package com.example.devsu.exam.application.infrastructure.controllers.impl;

import com.example.devsu.exam.application.infrastructure.domain.response.AccountDtoResponse;
import com.example.devsu.exam.application.infrastructure.services.interfaces.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountControllerImplTest {

    public static final Long accountId = 1L;
    private AccountControllerImpl controller;
    @Mock
    private AccountService service;


    @BeforeEach()
    void setUp(){
        controller = new AccountControllerImpl(service);
    }

    @Test
    void shouldReturn200WhenRetrieveAllAccounts(){

        when(service.getAllAccounts()).thenReturn(Optional.of(Collections.emptyList()));

        ResponseEntity<List<AccountDtoResponse>> response = controller.getAllAccounts();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldReturn200WhenRetrieveAccountById(){

        when(service.getAccount(accountId)).thenReturn(Optional.of(AccountDtoResponse.builder().build()));

        ResponseEntity<AccountDtoResponse> response = controller.getAccount(accountId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}