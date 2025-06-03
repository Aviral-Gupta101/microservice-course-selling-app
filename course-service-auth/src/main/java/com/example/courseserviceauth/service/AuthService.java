package com.example.courseserviceauth.service;

import com.example.courseserviceauth.clients.UserClient;
import com.example.courseserviceauth.dto.SignupSigninDto;
import com.example.courseserviceauth.exceptions.custom_exception.ApiException;
import com.example.courseserviceauth.security.util.JwtUtil;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserClient userClient;


    public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserClient userClient) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userClient = userClient;
    }

    public void signup(SignupSigninDto dto){

        // encode password before sending to the client
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        try {
            userClient.registerUser(dto);
        } catch (FeignException e) {

            if (e.status() == HttpStatus.CONFLICT.value())
                throw new ApiException(HttpStatus.valueOf(e.status()), "User already exists");
            throw e;
        }
    }

    public String login(SignupSigninDto dto){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );
            return jwtUtil.generateToken(dto.getUsername());

        } catch (BadCredentialsException e) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }
}
