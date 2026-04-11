package com.example.PFT.Controllers;


import com.example.PFT.Models.Dtos.AuthenticationRequest;
import com.example.PFT.Models.AuthenticationResponse;
import com.example.PFT.Models.Dtos.RegisterRequest;
import com.example.PFT.Services.AuthenticationService;
import com.example.PFT.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    private final UserService userService;
    //form and button
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> addUser(@RequestBody RegisterRequest request){
        System.out.println("Register request received for: " + request.getUsername());
        return ResponseEntity.ok(authService.register(request));
        //request.getUsername()

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }


}
