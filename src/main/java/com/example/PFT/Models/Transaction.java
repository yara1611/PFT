package com.example.PFT.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.ConstructorParameters;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private TransactionType transactionType; // e.g. "Deposit", "Withdrawal", "Send Money"

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @PrePersist
    protected void onCreate() {
        date = new Date(); // Sets the current timestamp before saving
    }

    public Transaction(Account account, Double amount, TransactionType transactionType) {
        this.account = account;
        this.amount = amount;
        this.transactionType = transactionType;
    }
}
