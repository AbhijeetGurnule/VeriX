package com.project.auth_app.controllers;

import com.project.auth_app.dtos.UserDto;
import com.project.auth_app.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // Create user api
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return  ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    // Get all user api
    @GetMapping
    public ResponseEntity<Iterable<UserDto>> getAllUsers(){
        return  ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    // Get user by email    |   This is called - "{email}" path url variable
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email){
        return  ResponseEntity.ok(userService.getUserByEmail(email));
    }


}
