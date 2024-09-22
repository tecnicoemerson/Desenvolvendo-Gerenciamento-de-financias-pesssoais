package com.GerenciamentodeFinancasPessoais.demo.service;
import com.GerenciamentodeFinancasPessoais.demo.model.Transaction;
import com.GerenciamentodeFinancasPessoais.demo.model.User;
import com.GerenciamentodeFinancasPessoais.demo.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void findAll() {
        // Cenário
        User user = new User(); // Supondo que você tenha um construtor ou método para criar um User
        user.setId(1L);

        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setUser(user); // Usar objeto User
        transaction1.setType("income");
        transaction1.setValue(BigDecimal.valueOf(100));
        transaction1.setDate(LocalDate.now());

        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction1));

        // Execução
        List<Transaction> transactions = transactionService.findAll();

        // Verificação
        assertThat(transactions).isNotEmpty();
        assertThat(transactions.size()).isEqualTo(1);
        assertThat(transactions.get(0).getType()).isEqualTo("income");
    }

    @Test
    void findById() {
        // Cenário
        User user = new User();
        user.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setUser(user); // Usar objeto User
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        // Execução
        Transaction foundTransaction = transactionService.findById(1L);

        // Verificação
        assertThat(foundTransaction).isNotNull();
        assertThat(foundTransaction.getId()).isEqualTo(1L);
    }

    @Test
    void save() {
        // Cenário
        User user = new User();
        user.setId(1L);

        Transaction transaction = new Transaction();
        transaction.setUser(user); // Usar objeto User
        transaction.setType("income");
        transaction.setValue(BigDecimal.valueOf(100));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        // Execução
        Transaction savedTransaction = transactionService.save(transaction);

        // Verificação
        assertThat(savedTransaction).isNotNull();
        assertThat(savedTransaction.getType()).isEqualTo("income");
    }

    @Test
    void update() {
        // Cenário
        User user = new User();
        user.setId(1L);

        Transaction existingTransaction = new Transaction();
        existingTransaction.setId(1L);
        existingTransaction.setUser(user); // Usar objeto User
        existingTransaction.setType("income");
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(existingTransaction));

        Transaction updatedTransaction = new Transaction();
        updatedTransaction.setUser(user); // Usar objeto User
        updatedTransaction.setType("expense");

        // Execução
        Transaction result = transactionService.update(1L, updatedTransaction);

        // Verificação
        assertThat(result).isNotNull();
        assertThat(result.getType()).isEqualTo("expense");
    }

    @Test
    void delete() {
        // Execução
        transactionService.delete(1L);

        // Verificação
        verify(transactionRepository, times(1)).deleteById(1L);
    }
}
