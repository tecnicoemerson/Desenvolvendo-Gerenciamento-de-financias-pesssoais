package com.GerenciamentodeFinancasPessoais.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.jetbrains.annotations.NotNull;

@Setter
@Getter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private String type; // "income" ou "expense"

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotNull
    private BigDecimal value;

    private LocalDate date;

    public Transaction(User user, String type, Category category, BigDecimal value, LocalDate date) {
        this.user = user;
        this.type = type;
        this.category = category;
        this.value = value;
        this.date = date;
    }

    public Transaction() {
        
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", user=" + user +
                ", type='" + type + '\'' +
                ", category=" + category +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}

