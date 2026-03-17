package com.sydney.transporttracker.controller;

import com.sydney.transporttracker.dto.LoginRequest;
import com.sydney.transporttracker.dto.RegisterRequest;
import com.sydney.transporttracker.model.User;
import com.sydney.transporttracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }
}
