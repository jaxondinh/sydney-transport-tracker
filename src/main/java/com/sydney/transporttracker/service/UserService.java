package com.sydney.transporttracker.service;

import com.sydney.transporttracker.dto.UserProfileResponse;
import com.sydney.transporttracker.model.User;
import com.sydney.transporttracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public UserProfileResponse getUserProfile(String username) {
        User user = userRepository.findByUsername(username);
        // Null check here might not be needed, user putting in this request should always have valid JWT
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setEmail(user.getEmail());
        userProfileResponse.setUsername(user.getUsername());
        userProfileResponse.setNotificationsEnabled(user.isNotificationsEnabled());
        return userProfileResponse;
    }
}
