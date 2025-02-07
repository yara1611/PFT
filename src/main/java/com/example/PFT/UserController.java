package com.example.PFT;

import com.example.PFT.Models.Account;
import com.example.PFT.Models.Transaction;
import com.example.PFT.Models.TransactionType;
import com.example.PFT.Models.User;
import com.example.PFT.Services.AccountService;
import com.example.PFT.Services.TransactionService;
import com.example.PFT.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;


@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok().body("Successfully Created User: "+user.getName()+" with username: "+user.getUsername());
    }

    @PostMapping("/addAccount")
    public ResponseEntity<String> addAccount(@RequestParam String username, @RequestParam Double balance){
        accountService.addAccount(username,balance);
        return ResponseEntity.ok().body("Successfully added account to user: "+username);
    }
    @PutMapping("/makeDeposit")
    public ResponseEntity<String> deposit(@RequestParam Long id, @RequestParam Double amount){
        accountService.makeDeposit(id,amount, TransactionType.DEPOSIT);
        return ResponseEntity.ok().body("Successfully desposited "+amount);
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
    @GetMapping("/all")
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/tran")
    public List<Transaction> getTrans(@RequestParam Long id){
        return transactionService.getAllTransactions(id);
    }
}
