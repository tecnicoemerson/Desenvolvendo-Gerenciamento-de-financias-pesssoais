package com.GerenciamentodeFinancasPessoais.demo.service;

import com.GerenciamentodeFinancasPessoais.demo.model.User;
import com.GerenciamentodeFinancasPessoais.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User update(Long id, User user){
        User userFound = findById(id);
        userFound.setName(user.getName());
        userFound.setEmail(user.getEmail());
        userFound.setPassword(user.getPassword());
        return userRepository.save(userFound);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}


