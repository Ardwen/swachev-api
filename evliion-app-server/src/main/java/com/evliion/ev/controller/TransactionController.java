package com.evliion.ev.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.evliion.ev.model.Transaction;
import com.evliion.ev.payload.ApiResponse;
import com.evliion.ev.payload.TransactionRequest;
import com.evliion.ev.payload.TransactionResponse;
import com.evliion.ev.security.CurrentUser;
import com.evliion.ev.security.UserPrincipal;
import com.evliion.ev.service.PollService;
import com.evliion.ev.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	
    @Autowired
    private TransactionService transactionService;
	
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public TransactionResponse addTransaction(@Valid @RequestBody TransactionRequest transactionRequest, @CurrentUser UserPrincipal currentUser) {
    	TransactionResponse transactionResponse = transactionService.createTransaction(currentUser, transactionRequest);
        
    	/*
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{transactionid}")
                .buildAndExpand(transaction.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Transaction created successfully"));
                */
    	return transactionResponse;
    }
}
