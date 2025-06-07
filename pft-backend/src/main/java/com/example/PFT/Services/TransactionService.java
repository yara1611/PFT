package com.example.PFT.Services;

import com.example.PFT.Models.Transaction;
import com.example.PFT.Models.enums.TransactionType;
import com.example.PFT.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public double revertTransaction(Long accountID){
        List<Transaction> transactions = transactionRepository.findTransactionsByAccount(accountID);
        try{
            Transaction lastTransaction = transactions.get(transactions.size()-1);
            if(lastTransaction.getTransactionType()== TransactionType.DEPOSIT){
                return -1*lastTransaction.getAmount();
            }else{
                return lastTransaction.getAmount();
            }
        } catch (Exception e) {
            throw new IllegalStateException("No Previous Transactions");
        }
    }

    //Optimize:Filter transactions by date range
    public List<Transaction> getAllTransactions(Long id, Date date){
        return transactionRepository.findTransactionsByDate(id,date);
    }

}
