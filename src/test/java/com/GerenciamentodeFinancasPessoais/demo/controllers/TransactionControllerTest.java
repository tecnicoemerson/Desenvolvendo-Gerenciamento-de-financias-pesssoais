package com.GerenciamentodeFinancasPessoais.demo.controllers;

import com.GerenciamentodeFinancasPessoais.demo.model.Transaction;
import com.GerenciamentodeFinancasPessoais.demo.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    // Esqueleto atual dos testes
    @Test
    void findAll() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setValue(BigDecimal.valueOf(100.00));
        transaction.setType("income");

        List<Transaction> transactions = Collections.singletonList(transaction);

        // Simula o comportamento do service
        Mockito.when(transactionService.findAll()).thenReturn(transactions);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("income"))
                .andExpect(jsonPath("$[0].value").value(100.00));
    }

    @Test
    void findById() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setValue(BigDecimal.valueOf(200.00));
        transaction.setType("expense");

        // Simula o comportamento do service
        Mockito.when(transactionService.findById(1L)).thenReturn(transaction);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("expense"))
                .andExpect(jsonPath("$.value").value(200.00));
    }

    @Test
    void save() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setValue(BigDecimal.valueOf(500.00));
        transaction.setType("income");

        // Simula o comportamento do service
        Mockito.when(transactionService.save(Mockito.any(Transaction.class))).thenReturn(transaction);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"type\": \"income\", \"value\": 500.00 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("income"))
                .andExpect(jsonPath("$.value").value(500.00));
    }

    @Test
    void update() throws Exception {
        Transaction updatedTransaction = new Transaction();
        updatedTransaction.setId(1L);
        updatedTransaction.setValue(BigDecimal.valueOf(300.00));
        updatedTransaction.setType("expense");

        // Simula o comportamento do service
        Mockito.when(transactionService.update(Mockito.eq(1L), Mockito.any(Transaction.class))).thenReturn(updatedTransaction);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/transactions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"type\": \"expense\", \"value\": 300.00 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("expense"))
                .andExpect(jsonPath("$.value").value(300.00));
    }

    @Test
    void delete() throws Exception {
        // Simula o comportamento do service
        Mockito.doNothing().when(transactionService).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/transactions/1"))
                .andExpect(status().isOk());
    }

}
