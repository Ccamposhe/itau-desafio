package com.ccamposhe.itau_desafio.controller;

import com.ccamposhe.itau_desafio.model.Transaction;
import com.ccamposhe.itau_desafio.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Transaction transaction){
        log.info("Receiving transaction: amount={}", transaction.getValor());
        transactionService.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        transactionService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
