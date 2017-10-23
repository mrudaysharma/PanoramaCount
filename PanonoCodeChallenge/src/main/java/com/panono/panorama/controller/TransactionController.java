/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panono.panorama.controller;

import com.panono.panorama.Default;
import com.panono.panorama.entity.EntityResponse;
import com.panono.panorama.entity.Transaction;
import com.panono.panorama.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static java.lang.System.currentTimeMillis;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 *
 * @author uday
 */

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EntityResponse<Void> create(@Valid @NotNull @RequestBody Transaction transaction) {
        return response(transaction);
    }

    private EntityResponse<Void> response(Transaction transaction) {
        if (currentTimeMillis() - transaction.getTimestamp() > Default.TIME_LIMIT) {
            return new EntityResponse<>(NO_CONTENT);
        } else {
            transactionService.create(transaction);
            return new EntityResponse<>(CREATED);
        }
    }

}
