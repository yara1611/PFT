package com.example.PFT.Controllers;

import com.example.PFT.Models.Transaction;
import com.example.PFT.Models.enums.TransactionType;
import com.example.PFT.Services.AccountService;
import com.example.PFT.Services.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/addAccount")
    public ResponseEntity<String> addAccount(@RequestParam String username, @RequestParam Double balance, @RequestParam(required = false) String name){
        accountService.addAccount(username,balance, name);
        return ResponseEntity.ok().body("Account \""+name+"\" is successfully added account to user: "+username);
    }
    @PutMapping("/makeDeposit")
    public ResponseEntity<Map<String, Object>> deposit(@RequestParam Long id, @RequestParam Double amount) {
        Map<String, Object> response = new HashMap<>();
        try {
            accountService.makeDeposit(id, amount, TransactionType.DEPOSIT);
            response.put("status", "success");
            response.put("balance", accountService.displayBalance(id)); // Assuming it returns balance
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @PutMapping("/withdraw")
    public ResponseEntity<Map<String, Object>> withdraw(@RequestParam Long id, @RequestParam Double amount) {
        Map<String, Object> response = new HashMap<>();
        try {
            accountService.makeDeposit(id, amount, TransactionType.WITHDRAW);
            response.put("status", "success");
            response.put("balance", accountService.displayBalance(id)); // Assuming it returns balance
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/displayBalance")
    public String displayBalance(@RequestParam Long accountId){
        return accountService.displayBalance(accountId);
    }

    @Tag(name="Admin")
    @GetMapping("/allTransactions")
    public List<Transaction> getTrans(@RequestParam Long accountId){
        return transactionService.getAllTransactions(accountId);
    }

    @Tag(name="Admin")
    @GetMapping("/revertTransaction")
    public void revertTransaction(@RequestParam Long id){
         accountService.revertT(id);
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<String> deleteAccount(@RequestParam Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok().body("Account successfully deleted.");
    }
}
