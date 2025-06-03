package com.example.courseserviceuser.service;

import com.example.courseserviceuser.entity.User;
import com.example.courseserviceuser.exceptions.custom_exception.ApiException;
import com.example.courseserviceuser.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findUsersByUsername(username).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Username not found"));
    }

    public User registerUser(String username, String password) {

        Optional<User> foundUser = userRepository.findUsersByUsername(username);

        if(foundUser.isPresent())
            throw new ApiException(HttpStatus.CONFLICT, "Username already exists");

        User newUser = User.builder()
                .username(username)
                .password(password)
                .build();

        return userRepository.save(newUser);
    }
}
