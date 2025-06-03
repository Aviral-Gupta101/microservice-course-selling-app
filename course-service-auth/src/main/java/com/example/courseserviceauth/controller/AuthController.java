package com.example.courseserviceauth.controller;

import com.example.courseserviceauth.clients.UserClient;
import com.example.courseserviceauth.dto.SignupSigninDto;
import com.example.courseserviceauth.entity.User;
import com.example.courseserviceauth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService, UserClient userClient) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupSigninDto dto){

        authService.signup(dto);
        return ResponseEntity.ok().body(Map.of("message", "Success"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SignupSigninDto dto){

        String token = authService.login(dto);
        return ResponseEntity.ok().body(Map.of("token", token));
    }
}
