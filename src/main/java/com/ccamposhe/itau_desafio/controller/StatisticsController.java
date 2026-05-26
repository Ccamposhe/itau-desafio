package com.ccamposhe.itau_desafio.controller;

import com.ccamposhe.itau_desafio.model.Statistics;
import com.ccamposhe.itau_desafio.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class StatisticsController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Statistics> getStatistics(){
        Statistics statistics = transactionService.getStatistics();
        return ResponseEntity.ok(statistics);
    }

}
