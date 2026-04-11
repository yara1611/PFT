package com.example.PFT.Controllers;

import com.example.PFT.Models.Dtos.ResponseDto;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/accounts")
@SecurityRequirement(name = "bearerAuth")
public class AccountController {


    //add current balance to transactions and the account transaction done from
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<String> addAccount(@RequestParam Double balance, @RequestParam(required = false) String name){
        User user = userService.getCurrentUser();
        accountService.addAccount(userService.getCurrentUser(),balance, name);
        return ResponseEntity.ok().body("Account \""+name+"\" is successfully added account to user "+user.getUsername());
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<ResponseDto> deposit(@PathVariable Long id, @RequestParam Double amount) {
        ResponseDto response = new ResponseDto();
        try {
            accountService.makeDeposit(id, amount, TransactionType.DEPOSIT);

            response.setStatus("Success");
            response.setMessage(accountService.displayBalance(id)); // Assuming it returns balance

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setStatus("Error");
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @PutMapping("/{id}/withdraw")
    public ResponseEntity<ResponseDto> withdraw(@PathVariable Long id, @RequestParam Double amount) {
        ResponseDto response = new ResponseDto();
        try {
            accountService.makeDeposit(id, amount, TransactionType.WITHDRAW);
            response.setStatus("Success");
            response.setMessage(accountService.displayBalance(id)); // Assuming it returns balance
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.setStatus("Error");
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    //displays balance of account with entered id not of the current user
    //so if user has access to id of another users account it can see it
    @GetMapping("/{id}/balance")
    public String displayBalance(@PathVariable Long accountId){
        return accountService.displayBalance(accountId);
    }

    @Tag(name="Admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long accountId){
        return ResponseEntity.ok().body(transactionService.getAllTransactions(accountId));
    }

    @Tag(name="Admin")
    @GetMapping("/{id}/transactions/last")
    public ResponseEntity<String> revertTransaction(@PathVariable Long id){
         accountService.revertTransaction(id);
        return ResponseEntity.ok().body("Transaction successfully reverted.");
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok().body("Account successfully deleted.");
    }
}
