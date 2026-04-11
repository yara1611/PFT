package com.example.PFT;


import com.example.PFT.Models.Account;
import com.example.PFT.Models.User;
import com.example.PFT.Models.enums.Role;
import com.example.PFT.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // Clear data to ensure a clean slate for every test run
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("yara");
        user.setPassword("password"); // If you have an encoder, use it here
        user.setRole(Role.USER);

        Account acc1 = new Account();
        acc1.setUser(user);
        acc1.setName("acc1");
        acc1.setBalance(1000.0);

        user.setAccount(List.of(acc1));

        // Save the user (this will also save the account because of CascadeType.ALL/PERSIST)
        userRepository.save(user);

        User intruder = new User("intruder_user", "intruder@email.com", "password");
        intruder.setRole(Role.USER);
        userRepository.save(intruder);
    }

    //TEST 1: Happy Path (User owns the account)
    @Test
    @WithUserDetails(value = "yara", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void getBalance_Success() throws Exception{
        User user = userRepository.findByUsername("yara").orElseThrow();
        Long actualId = user.getAccount().get(0).getAccountId();

        mockMvc.perform(get("/api/v1/accounts/" + actualId + "/balance"))
                .andExpect(status().isOk());    }

    // TEST 2: Sad Path (User is logged in but tries to see someone else's account)
    @Test
    @WithUserDetails(value = "intruder_user", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void getBalance_Forbidden() throws Exception{
        User yara = userRepository.findByUsername("yara").orElseThrow();
        Long yarasAccountId = yara.getAccount().get(0).getAccountId();
        mockMvc.perform(get("/api/v1/accounts/" + yarasAccountId + "/balance"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Not Allowed"));    }

    // TEST 3: Unauthorized Path (No user logged in at all)
    @Test
    void getBalance_Unauthorized() throws Exception {
        // No @WithMockUser here
        mockMvc.perform(get("/api/v1/accounts/1/balance"))
                .andExpect(status().isUnauthorized()) // Should trigger your AuthenticationEntryPoint
                .andExpect(jsonPath("$.error").exists());
    }
}
