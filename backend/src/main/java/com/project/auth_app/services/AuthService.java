package com.project.auth_app.services;

import com.project.auth_app.dtos.UserDto;

public interface AuthService {
    // Register user
    UserDto registerUser(UserDto userDto);

    // TODO: Login user
}
