package com.picpaysimplificado.controllers;

import com.picpaysimplificado.domain.transaction.dto.request.TransactionDTO;
import com.picpaysimplificado.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService service;

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody TransactionDTO transaction) {
        return ResponseEntity.ok().body(service.createTransaction(transaction));
    }
}
