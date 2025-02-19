package com.example.PFT.Services;

import com.example.PFT.Models.Account;
import com.example.PFT.Models.Transaction;
import com.example.PFT.Repositories.AccountRepository;
import com.example.PFT.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions(Long id){
        return transactionRepository.findTransactionsByAccount(id);
    }

    public void logTransaction(Transaction transaction){
        System.out.println(transaction.toString());
        transactionRepository.save(transaction);
    }

    //TODO: revert the transaction by returning the balance to the previous status
    public void revertTransaction(Transaction transaction){}

}
