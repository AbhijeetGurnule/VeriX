package com.project.auth_app.services.impl;

import com.project.auth_app.dtos.UserDto;
import com.project.auth_app.services.AuthService;
import com.project.auth_app.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public UserDto registerUser(UserDto userDto) {
//        login
//        verify email
//        verify password

        UserDto userDto1 = userService.createUser(userDto);
        return userDto1;
    }
}
