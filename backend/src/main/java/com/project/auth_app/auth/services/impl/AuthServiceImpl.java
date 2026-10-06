package com.project.auth_app.auth.services.impl;

import com.project.auth_app.auth.payload.UserDto;
import com.project.auth_app.auth.services.AuthService;
import com.project.auth_app.auth.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {
//        login
//        verify email
//        verify password
//        default roles
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userService.createUser(userDto);
    }
}
