package com.example.PFT.Controllers;

import com.example.PFT.Models.Transaction;
import com.example.PFT.Models.enums.TransactionType;
import com.example.PFT.Services.AccountService;
import com.example.PFT.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/addAccount")
    public ResponseEntity<String> addAccount(@RequestParam String username, @RequestParam Double balance, @RequestParam String name){
        accountService.addAccount(username,balance, name);
        return ResponseEntity.ok().body("Account \""+name+"\" is successfully added account to user: "+username);
    }
    @PutMapping("/makeDeposit")
    public ResponseEntity<String> deposit(@RequestParam Long id, @RequestParam Double amount){
        accountService.makeDeposit(id,amount, TransactionType.DEPOSIT);
        return ResponseEntity.ok().body("Successfully deposited "+amount);
    }
    @PutMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam Long id, @RequestParam Double amount){
        try {
            accountService.makeDeposit(id,amount, TransactionType.WITHDRAW);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Successfully withdrawn "+amount);

    }

    @GetMapping("/allTransactions")
    public List<Transaction> getTrans(@RequestParam Long accountId){
        return transactionService.getAllTransactions(accountId);
    }
}
