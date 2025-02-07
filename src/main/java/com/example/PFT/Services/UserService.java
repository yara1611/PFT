package com.example.PFT.Services;

import com.example.PFT.Models.User;
import com.example.PFT.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public void createUser(User user){
       userRepository.save(user);
    }
    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
