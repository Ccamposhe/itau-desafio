package com.ccamposhe.itau_desafio.service;

import com.ccamposhe.itau_desafio.model.Statistics;
import com.ccamposhe.itau_desafio.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Service
public class TransactionService {

    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public void save(Transaction transaction){
        if (transaction.getDataHora().isAfter(OffsetDateTime.now())){
            throw new IllegalArgumentException("A data da transação não pode ser futura.");
        }
        if (transaction.getValor().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Numeros negativos não são aceitos na transação");
        }

        transactions.add(transaction);
        log.info("Transação salva: valor={}, dataHora={}", transaction.getValor(), transaction.getDataHora());
    }

    public void deleteAll(){
        transactions.clear();
        log.info("Todas as transações deletadas");
    }

    public Statistics getStatistics(){
        DoubleSummaryStatistics stats = transactions.stream()
                .filter(t -> t.getDataHora().isAfter(OffsetDateTime.now().minusSeconds(60)))
                .mapToDouble(t -> t.getValor().doubleValue())
                .summaryStatistics();

        if (stats.getCount() == 0){
            return Statistics.empty();
        }
        return new Statistics(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax()
        );
    }
}
