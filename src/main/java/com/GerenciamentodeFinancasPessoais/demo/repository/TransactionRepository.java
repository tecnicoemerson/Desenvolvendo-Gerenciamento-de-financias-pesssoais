package com.GerenciamentodeFinancasPessoais.demo.repository;

import com.GerenciamentodeFinancasPessoais.demo.model.Transaction;
import com.GerenciamentodeFinancasPessoais.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}
