package com.example.PFT.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String name;
    private String role;
}
