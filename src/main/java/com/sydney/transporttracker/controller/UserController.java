package com.sydney.transporttracker.controller;

import com.sydney.transporttracker.dto.UserProfileResponse;
import com.sydney.transporttracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/me")
    public UserProfileResponse myProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUserProfile(userDetails.getUsername());
    }
}
