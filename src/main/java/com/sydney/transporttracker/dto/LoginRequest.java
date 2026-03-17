package com.sydney.transporttracker.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Please enter a username")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "Please enter a password")
    private String password;
}
