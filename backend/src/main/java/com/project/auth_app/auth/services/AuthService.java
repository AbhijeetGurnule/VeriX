package com.project.auth_app.auth.services;

import com.project.auth_app.auth.payload.UserDto;

public interface AuthService {
    // Register user
    UserDto registerUser(UserDto userDto);

    // TODO: Login user
}
