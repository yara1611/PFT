package com.example.PFT.Controllers;

import com.example.PFT.Models.Dtos.UserDTO;
import com.example.PFT.Models.User;
import com.example.PFT.Services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/demo")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello User");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> sayHelloAdmin(){
        return ResponseEntity.ok("Hello Admin");
    }

    @GetMapping("/allUsers")
    public List<UserDTO> getUsers(){
        return userService.getUsers().stream().map(u-> new
                UserDTO(u.getUsername(),u.getEmail(),u.getName(),u.getRole().name()))
                .collect(Collectors.toList());
    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserDTO> getCurrentUser(){
        return ResponseEntity.ok(userService.getUser());
    }
}
