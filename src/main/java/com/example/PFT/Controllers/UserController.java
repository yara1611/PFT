package com.example.PFT.Controllers;


import com.example.PFT.Models.User;
import com.example.PFT.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
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


    @PutMapping("/changePassword")
    public ResponseEntity<String> editUser(@RequestParam Long userId, @RequestParam String newPass){
        userService.changePassword(userId,newPass);
        return ResponseEntity.ok().body("Password is successfully updated");
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("User successfully deleted");
    }


    @GetMapping("/login")
    public ResponseEntity<String> login (@RequestParam String username, @RequestParam String Password){
        User user=userService.getUserByUsername(username);
        boolean checkPass = userService.checkPass(user,Password);
        if(!checkPass){
            return ResponseEntity.badRequest().body("Wrong username or password");
        }
        return ResponseEntity.ok().body("User "+username+" successfully Logged in");
    }


   //TODO: Make it not return the password
    @Tag(name="Admin")
    @GetMapping("/allUsers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

}
