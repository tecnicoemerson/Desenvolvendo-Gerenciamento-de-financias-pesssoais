package com.GerenciamentodeFinancasPessoais.demo.controllers;

import com.GerenciamentodeFinancasPessoais.demo.model.User;
import com.GerenciamentodeFinancasPessoais.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void findAll() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        List<User> users = Collections.singletonList(user);

        // Simula o comportamento do service
        Mockito.when(userService.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }


    @Test
    void findById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Jane Doe");

        // Simula o comportamento do service
        Mockito.when(userService.findById(1L)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }


    @Test
    void save() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("John Smith");

        // Simula o comportamento do service
        Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"John Smith\", \"email\": \"john.smith@example.com\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Smith"));
    }


    @Test
    void update() throws Exception {
        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName("John Updated");

        // Simula o comportamento do service
        Mockito.when(userService.update(Mockito.eq(1L), Mockito.any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"John Updated\", \"email\": \"john.updated@example.com\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Updated"));
    }


    @Test
    void delete() throws Exception {
        // Simula o comportamento do service
        Mockito.doNothing().when(userService).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1"))
                .andExpect(status().isOk());
    }

}
