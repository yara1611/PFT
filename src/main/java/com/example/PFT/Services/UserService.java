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

    public void editUser(Long userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id: " + userId + " not found"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setName(updatedUser.getName());
        /* existingUser.setEmail(updatedUser.getEmail());
         * existingUser.setRoles(updatedUser.getRoles()); //If roles exist in your user model
         */

        userRepository.save(existingUser);
    }

    public void changePassword(Long userId, String newPassword){
        User currentUser = userRepository.findById(userId).orElseThrow(()->new IllegalStateException("User with id: "+userId+" not found"));
        currentUser.setPassword(newPassword); // Consider hashing passwords before saving
        userRepository.save(currentUser);
    }
}
