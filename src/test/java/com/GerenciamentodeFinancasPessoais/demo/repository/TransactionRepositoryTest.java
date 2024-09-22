package com.GerenciamentodeFinancasPessoais.demo.repository;

import com.GerenciamentodeFinancasPessoais.demo.model.Category;
import com.GerenciamentodeFinancasPessoais.demo.model.Transaction;
import com.GerenciamentodeFinancasPessoais.demo.model.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findByUserId() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        entityManager.persist(user);

        Category category = new Category();
        category.setName("Food");
        category.setUser(user);
        entityManager.persist(category.getUser());

        Transaction transaction1 = new Transaction();
        transaction1.setValue(BigDecimal.valueOf(100.00));
        transaction1.setType("income");
        transaction1.setUser(user); // Usa o objeto User
        transaction1.setCategory(category); // Usa o objeto Category
        entityManager.persist(transaction1.getUser());

        Transaction transaction2 = new Transaction();
        transaction2.setValue(BigDecimal.valueOf(50.00));
        transaction2.setType("expense");
        transaction2.setUser(user); // Usa o objeto User
        transaction2.setCategory(category); // Usa o objeto Category
        entityManager.persist(transaction2.getUser());

        entityManager.flush();

        // Agora, você pode chamar o repositório
        List<Transaction> foundTransactions = transactionRepository.findByUser(user);

        assertEquals(2, foundTransactions.size());
        assertTrue(foundTransactions.stream().anyMatch(transaction -> transaction.getValue().equals(BigDecimal.valueOf(100.00))));
        assertTrue(foundTransactions.stream().anyMatch(transaction -> transaction.getValue().equals(BigDecimal.valueOf(50.00))));
    }

}
