package com.example.PFT;

import com.example.PFT.Models.User;
import com.example.PFT.Services.UserService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SpringBootApplication
@RequestMapping("api/v1")
@SecurityScheme(name = "bearerAuth",scheme = "bearer",bearerFormat = "JWT", type = SecuritySchemeType.HTTP,in=SecuritySchemeIn.HEADER)
public class PftApplication {

	public static void main(String[] args) {
		SpringApplication.run(PftApplication.class, args);
	}



}
