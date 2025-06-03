package com.example.courseserviceauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/test")
public class TestController {

    @GetMapping
    public ResponseEntity<?> test(@RequestHeader("X-Username") String username) {

        System.out.println("Custom username: " + username);
        return ResponseEntity.ok().body("This is a secret");
    }
}
