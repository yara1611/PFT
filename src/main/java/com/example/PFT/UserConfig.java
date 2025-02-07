package com.example.PFT;

import com.example.PFT.Models.User;
import com.example.PFT.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User user1 = new User("yara","yara1","1611");
            repository.saveAll(List.of(user1));
        };
    }
}
