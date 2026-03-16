package com.sydney.transporttracker.service;

import com.sydney.transporttracker.dto.LoginRequest;
import com.sydney.transporttracker.service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    public String login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtService.generateToken(username);
    }
}
