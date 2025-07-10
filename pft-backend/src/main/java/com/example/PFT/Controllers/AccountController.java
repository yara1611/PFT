package com.example.PFT.Controllers;

import com.example.PFT.Models.Transaction;
import com.example.PFT.Models.User;
import com.example.PFT.Models.enums.TransactionType;
import com.example.PFT.Services.AccountService;
import com.example.PFT.Services.TransactionService;
import com.example.PFT.Services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
@SecurityRequirement(name = "bearerAuth")
public class AccountController {


    //add current balance to transactions and the account transaction done from
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @PostMapping("/addAccount")
    public ResponseEntity<String> addAccount(@RequestParam Double balance, @RequestParam(required = false) String name){
        User user = userService.getCurrentUser();
        accountService.addAccount(userService.getCurrentUser(),balance, name);
        return ResponseEntity.ok().body("Account \""+name+"\" is successfully added account to user "+user.getUsername());
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

    //displays balance of account with entered id not of the current user
    //so if user has access to id of another users account it can see it
    @GetMapping("/displayBalance")
    public String displayBalance(@RequestParam Long accountId){
        return accountService.displayBalance(accountId);
    }

    @Tag(name="Admin")
    @GetMapping("/allTransactions")
    public ResponseEntity<List<Transaction>> getTransactions(@RequestParam Long accountId){
        return ResponseEntity.ok().body(transactionService.getAllTransactions(accountId));
    }

    @Tag(name="Admin")
    @GetMapping("/revertTransaction")
    public ResponseEntity<String> revertTransaction(@RequestParam Long id){
         accountService.revertT(id);
        return ResponseEntity.ok().body("Transaction successfully reverted.");
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<String> deleteAccount(@RequestParam Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok().body("Account successfully deleted.");
    }
}
