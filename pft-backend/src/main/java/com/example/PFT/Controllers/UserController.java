package com.example.PFT.Controllers;

import com.example.PFT.Models.Account;
import com.example.PFT.Models.Dtos.AccountDto;
import com.example.PFT.Models.Dtos.ChangePasswordRequest;
import com.example.PFT.Models.Dtos.EditUserRequest;
import com.example.PFT.Models.User;
import com.example.PFT.Services.AccountService;
import com.example.PFT.Services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @PatchMapping("/me")
    public ResponseEntity<String> editUser(@RequestBody EditUserRequest newUser){
        userService.editUser(userService.getCurrentUser(),newUser);
        return ResponseEntity.ok().body("User \""+newUser.getUsername()+"\" is successfully updated");
    }

    @PatchMapping("/me/password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request){
        userService.changePassword(request);
        return ResponseEntity.ok().body("Password is successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("User successfully deleted");
    }
    //TODO:should this be here??
    @GetMapping("/me/accounts")
    public List<AccountDto> allAccounts(){
        return accountService.getAllAccounts(userService.getCurrentUser());
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(){
        return ResponseEntity.ok(userService.getCurrentUser());
    }
}
