package com.example.PFT.Models.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EditUserRequest {
    private String username;
    private String name;
}

