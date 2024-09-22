package com.GerenciamentodeFinancasPessoais.demo.service;

import com.GerenciamentodeFinancasPessoais.demo.model.User;
import com.GerenciamentodeFinancasPessoais.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        // Cenário
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1));

        // Execução
        List<User> users = userService.findAll();

        // Verificação
        assertThat(users).isNotEmpty();
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getName()).isEqualTo("John Doe");
    }

    @Test
    void findById() {
        // Cenário
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Execução
        User foundUser = userService.findById(1L);

        // Verificação
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(1L);
    }

    @Test
    void save() {
        // Cenário
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Execução
        User savedUser = userService.save(user);

        // Verificação
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("John Doe");
    }

    @Test
    void update() {
        // Cenário
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("John Doe");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        User updatedUser = new User();
        updatedUser.setName("Jane Doe");

        // Execução
        User result = userService.update(1L, updatedUser);

        // Verificação
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Jane Doe");
    }

    @Test
    void delete() {
        // Execução
        userService.delete(1L);

        // Verificação
        verify(userRepository, times(1)).deleteById(1L);
    }
}
