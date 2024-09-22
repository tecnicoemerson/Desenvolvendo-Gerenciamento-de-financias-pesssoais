package com.GerenciamentodeFinancasPessoais.demo.controllers;

import com.GerenciamentodeFinancasPessoais.demo.model.Transaction;
import com.GerenciamentodeFinancasPessoais.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> findAll(){
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public Transaction findById(@PathVariable Long id){
        return transactionService.findById(id);
    }

    @PostMapping
    public Transaction save(@RequestBody Transaction transaction){
        return transactionService.save(transaction);
    }

    @PutMapping("/{id}")
    public Transaction update(@PathVariable Long id, @RequestBody Transaction transaction){
        return transactionService.update(id, transaction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        transactionService.delete(id);
    }

}