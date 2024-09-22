package com.GerenciamentodeFinancasPessoais.demo.service;

import com.GerenciamentodeFinancasPessoais.demo.model.Transaction;
import com.GerenciamentodeFinancasPessoais.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id)); // Exceção personalizada
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction update(Long id, Transaction transaction) {
        Transaction transactionFound = findById(id);
        transactionFound.setUser(transaction.getUser()); // Associe o objeto User
        transactionFound.setCategory(transaction.getCategory()); // Associe o objeto Category
        transactionFound.setValue(transaction.getValue());
        transactionFound.setType(transaction.getType()); // Atualiza o tipo também
        transactionFound.setDate(transaction.getDate()); // Atualiza a data se necessário
        return transactionRepository.save(transactionFound);
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
}

