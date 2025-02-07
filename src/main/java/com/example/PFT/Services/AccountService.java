package com.example.PFT.Services;

import com.example.PFT.Models.Account;
import com.example.PFT.Models.Transaction;
import com.example.PFT.Models.TransactionType;
import com.example.PFT.Models.User;
import com.example.PFT.Repositories.AccountRepository;
import com.example.PFT.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionService transactionService;
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    //make DTOs later
    public void addAccount(String username, Double balance){
        User user = Optional.ofNullable(userRepository.findUserByUsername(username))
                .orElseThrow(() -> new IllegalStateException("User not found"));
        Account account=new Account();
        account.setUser(user);
        account.setBalance(balance);
        accountRepository.save(account);
    }

    public void makeDeposit(Long accountID, Double amount,TransactionType type){
        Account account = accountRepository.findById(accountID).get();
        if(account==null){
            throw new IllegalStateException("Account with id: "+accountID+" is not found");
        }
        if(type.equals(TransactionType.DEPOSIT))
        {
            account.setBalance(account.getBalance()+amount);
        }
        else if(type.equals(TransactionType.WITHDRAW)){
            if(account.getBalance()<amount)
            {
                throw new RuntimeException("No Sufficient Funds");
            }
            account.setBalance(account.getBalance()-amount);
        }
        System.out.println("LOG:");
        transactionService.logTransaction(new Transaction(account,amount, type));
        accountRepository.save(account);
    }

}
