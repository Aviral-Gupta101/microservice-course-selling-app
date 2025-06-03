package com.example.courseserviceuser.controller;

import com.example.courseserviceuser.dto.SignupSigninDto;
import com.example.courseserviceuser.entity.User;
import com.example.courseserviceuser.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {

        User foundUser = userService.findByUsername(username);
        return ResponseEntity.ok().body(foundUser);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@Valid @RequestBody SignupSigninDto dto) {

        User savedUser = userService.registerUser(dto.getUsername(), dto.getPassword());
        return ResponseEntity.ok().body(savedUser);
    }
}
