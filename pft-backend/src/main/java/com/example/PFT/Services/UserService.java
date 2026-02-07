package com.example.PFT.Services;

import com.example.PFT.Models.Dtos.ChangePasswordRequest;
import com.example.PFT.Models.Dtos.EditUserRequest;
import com.example.PFT.Models.Dtos.UserDTO;
import com.example.PFT.Models.User;
import com.example.PFT.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalStateException("User is not found"));
        userRepository.delete(user);
    }


    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public UserDTO getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal(); //NOTE: commonly used in Spring Security to get the currently authenticated user's details.
        return new UserDTO(
                currentUser.getUsername(),
                currentUser.getEmail(),
                currentUser.getName(),
                currentUser.getRole() != null ? currentUser.getRole().name() : null
        );
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    //Todo: add it to register
    public boolean checkPass(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(regex);
    }


    public void editUser(User currentUser, EditUserRequest updatedUser) {
        try {
            currentUser.setUsername(updatedUser.getUsername());
            currentUser.setName(updatedUser.getName());
            /* existingUser.setEmail(updatedUser.getEmail());
             * existingUser.setRoles(updatedUser.getRoles()); //If roles exist in your user model
             */
            userRepository.save(currentUser);
        }
        catch (IllegalStateException e){
            e.getLocalizedMessage();
        }


    }

    public void changePassword(ChangePasswordRequest request){
        User currentUser = getCurrentUser();
        if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect.");
        }
        currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(currentUser);
    }

}
