package com.GerenciamentodeFinancasPessoais.demo.repository;

import com.GerenciamentodeFinancasPessoais.demo.model.Category;
import com.GerenciamentodeFinancasPessoais.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByUserId() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        entityManager.persist(user);

        Category category1 = new Category();
        category1.setName("Food");
        category1.setUser(user); // Usa o objeto User
        entityManager.persist(category1);

        Category category2 = new Category();
        category2.setName("Travel");
        category2.setUser(user); // Usa o objeto User
        entityManager.persist(category2);

        entityManager.flush();

        // A consulta deve ser adaptada para buscar pelo objeto User
        List<Category> foundCategories = categoryRepository.findByUserId(user.getId());

        assertEquals(2, foundCategories.size());
        assertTrue(foundCategories.stream().anyMatch(category -> category.getName().equals("Food")));
        assertTrue(foundCategories.stream().anyMatch(category -> category.getName().equals("Travel")));
    }
}
