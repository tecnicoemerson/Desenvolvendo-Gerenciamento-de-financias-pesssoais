package com.GerenciamentodeFinancasPessoais.demo.repository;

import com.GerenciamentodeFinancasPessoais.demo.model.User;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void findByEmail() {
        // Cenário: Cria um usuário e persiste no banco de dados
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        entityManager.persist(user);
        entityManager.flush(); // Certifica-se de que o usuário foi salvo

        // Execução: Busca o usuário pelo email
        User foundUser = userRepository.findByEmail("john.doe@example.com");

        // Verificação: Verifica se o usuário foi encontrado corretamente
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(foundUser.getName()).isEqualTo("John Doe");
    }
}