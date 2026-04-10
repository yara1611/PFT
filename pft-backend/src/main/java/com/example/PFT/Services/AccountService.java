package com.example.PFT.Services;

import com.example.PFT.Models.Account;
import com.example.PFT.Models.Transaction;
import com.example.PFT.Models.enums.TransactionType;
import com.example.PFT.Models.User;
import com.example.PFT.Repositories.AccountRepository;
import com.example.PFT.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    //TODO:make DTOs
    public void addAccount(User user,Double balance, String name){

        Account account=new Account();
        account.setName(name==null? "New Account":name);
        account.setUser(user);
        account.setBalance(balance==null?0.0:balance);
        accountRepository.save(account);
    }

    public List<Account> getAllAccounts(User user){
        return accountRepository.findAccountsByUser(user.getUserId());
    }

    //TODO:Change Name
    public void makeDeposit(Long accountID, Double amount,TransactionType type){

       Account account = accountRepository.findById(accountID)
               .orElseThrow(() -> new IllegalStateException("Account with id: " + accountID + " is not found"));

       if (type==TransactionType.DEPOSIT) {
            account.setBalance(account.getBalance() + amount);
        } else if (type==TransactionType.WITHDRAW){
            if(account.getBalance()<amount)
            {
                throw new RuntimeException("Insufficient Funds");
            }
            account.setBalance(account.getBalance()-amount);
        }

       //TODO make a logger later
        System.out.println("LOG:");

       //TODO add category
        transactionService.logTransaction(new Transaction(account,amount, type));
        accountRepository.save(account);
    }

    public void deleteAccount(Long id){
        Account account = accountRepository.findById(id).orElseThrow(()->new IllegalStateException("Account with id: "+id+" is not found"));
        accountRepository.delete(account);
    }

    public String displayBalance(Long accountID){
        User user = userService.getCurrentUser();

         Account account = accountRepository.findById(accountID)
                .orElseThrow(() -> new IllegalStateException("Account with id: " + accountID + " is not found"));
         if(!user.getAccount().contains(account))
         {
             throw new IllegalStateException("Not Allowed");
         }
         return account.getBalance().toString();
    }

    @Transactional
    public void revertTransaction(Long accountID){
        double bal = transactionService.lastTransaction(accountID);
        Account account = accountRepository.findById(accountID)
                .orElseThrow(() -> new IllegalStateException("Account with id: " + accountID + " is not found"));
        account.setBalance(account.getBalance()+bal);
        transactionService.logTransaction(new Transaction(account,bal,TransactionType.REVERT));

    }

}
