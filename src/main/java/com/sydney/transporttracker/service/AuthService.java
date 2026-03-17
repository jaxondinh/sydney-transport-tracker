package com.sydney.transporttracker.service;

import com.sydney.transporttracker.dto.LoginRequest;
import com.sydney.transporttracker.dto.RegisterRequest;
import com.sydney.transporttracker.model.User;
import com.sydney.transporttracker.repository.UserRepository;
import com.sydney.transporttracker.service.security.JwtService;
import com.sydney.transporttracker.service.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    public String login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtService.generateToken(username);
    }
    public User register(RegisterRequest registerRequest) {
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setRole("ROLE_USER");
        newUser.setEmail(registerRequest.getEmail());
        return userRepository.save(newUser);
    }
}
