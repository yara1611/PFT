package com.example.PFT.config;

import com.example.PFT.Models.Account;
import com.example.PFT.Models.User;
import com.example.PFT.Models.enums.Role;
import com.example.PFT.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class UserConfig {
//TODO:remove this later
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User user1 = new User("yara","yara1","1611");
            user1.setRole(Role.USER);

            Account acc1=new Account();
            acc1.setUser(user1);
            acc1.setName("acc1");
            acc1.setBalance(1000.0);
            Account acc2=new Account();
            acc2.setUser(user1);
            acc2.setName("accDel");
            acc2.setBalance(1000.0);
            user1.setAccount(List.of(acc1,acc2));
            repository.saveAll(List.of(user1));
        };
    }
}
