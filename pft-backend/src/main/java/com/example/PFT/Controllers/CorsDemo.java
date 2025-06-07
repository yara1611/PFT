package com.example.PFT.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CorsDemo {

    @GetMapping("/")
    public String home() {
        return "tester.html";
    }

    @PostMapping("/test")
    @ResponseBody
    public String test() {
        return "Hello World!";
    }
}
