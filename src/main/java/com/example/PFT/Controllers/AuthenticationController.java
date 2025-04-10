package com.example.PFT.Controllers;


import com.example.PFT.Models.Auth.AuthenticationRequest;
import com.example.PFT.Models.Auth.AuthenticationResponse;
import com.example.PFT.Models.Auth.RegisterRequest;
import com.example.PFT.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    //form and button
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> addUser(@RequestBody RegisterRequest request){ //@RequestBody -> in body

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){ //@RequestBody -> in body
        return ResponseEntity.ok(authService.authenticate(request));
    }


}
