package com.example.PFT.Controllers;

import com.example.PFT.Models.Transaction;
import com.example.PFT.Models.User;
import com.example.PFT.Services.TransactionService;
import com.example.PFT.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    //form and button
    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user){ //@RequestBody -> in body
        userService.createUser(user);
        return ResponseEntity.ok().body("Successfully Created User: "+user.getName()+" with username: "+user.getUsername());
    }

    @PutMapping("/editUser")
    public ResponseEntity<String> editUser(@RequestParam Long userId, @RequestBody User newUser){
        userService.editUser(userId,newUser);
        return ResponseEntity.ok().body("User \""+newUser.getUsername()+"\" is successfully updated");
    }

    //TODO change password
    //TODO LogIn

    //Admin Stuff
    @GetMapping("/allUsers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

}
