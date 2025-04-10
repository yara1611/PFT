package com.example.PFT.Controllers;


import com.example.PFT.Models.Dtos.ChangePasswordRequest;
import com.example.PFT.Models.Dtos.EditUserRequest;
import com.example.PFT.Models.Dtos.UserDTO;
import com.example.PFT.Models.User;
import com.example.PFT.Services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    @Autowired
    private UserService userService;


    @PutMapping("/editUser")
    public ResponseEntity<String> editUser(@RequestBody EditUserRequest newUser){
        userService.editUser(userService.getCurrentUser(),newUser);
        return ResponseEntity.ok().body("User \""+newUser.getUsername()+"\" is successfully updated");
    }


    @PutMapping("/changePassword")
    public ResponseEntity<String> editUser(@RequestBody ChangePasswordRequest request){
        userService.changePassword(request);
        return ResponseEntity.ok().body("Password is successfully updated");
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("User successfully deleted");
    }






}
