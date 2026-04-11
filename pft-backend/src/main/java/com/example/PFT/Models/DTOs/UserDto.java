package com.example.PFT.Models.DTOs;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    @Null
    private String email;
    private String name;
    private String role;
}
