package com.example.PFT.Models.Dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
