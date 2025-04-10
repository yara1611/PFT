package com.example.PFT.Models.Dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class EditUserRequest {
    private String username;
    private String name;
}

