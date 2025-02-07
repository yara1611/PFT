package com.example.PFT.Models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String transactionType; // e.g. "Deposit", "Withdrawal", "Send Money"

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
