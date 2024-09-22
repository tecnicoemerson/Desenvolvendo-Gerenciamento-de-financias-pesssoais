package com.GerenciamentodeFinancasPessoais.demo.repository;

import com.GerenciamentodeFinancasPessoais.demo.model.Category;
import com.GerenciamentodeFinancasPessoais.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);

    List<Category> findByUserId(Long id);
}
