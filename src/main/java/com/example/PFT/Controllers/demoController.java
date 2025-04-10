package com.example.PFT.Controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@SecurityRequirement(name = "bearerAuth")
public class demoController {

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello User");
    }

    @GetMapping("admin")
    public ResponseEntity<String> sayHelloAdmin(){
        return ResponseEntity.ok("Hello Admin");
    }
}
