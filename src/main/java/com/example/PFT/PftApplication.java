package com.example.PFT;

import com.example.PFT.Models.User;
import com.example.PFT.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SpringBootApplication
@RequestMapping("api/v1")
public class PftApplication {

	public static void main(String[] args) {
		SpringApplication.run(PftApplication.class, args);
	}



}
