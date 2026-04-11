package com.example.PFT.Models.Dtos;

import com.example.PFT.Models.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {
    private Long transactionId;
    private Double amount;
    private TransactionType transactionType;
    private Date date;
}
