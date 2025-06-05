package com.example.courseserviceuser.controller;

import com.example.courseserviceuser.dto.SignupSigninDto;
import com.example.courseserviceuser.dto.UserCourseDto;
import com.example.courseserviceuser.entity.Course;
import com.example.courseserviceuser.entity.Transaction;
import com.example.courseserviceuser.entity.User;
import com.example.courseserviceuser.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;


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

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> purchaseCourse(@PathVariable Integer courseId, HttpServletRequest request) {
        String username = request.getHeader("X-Username");
        Transaction transaction = userService.buyCourse(username, courseId);
        return ResponseEntity.ok().body(transaction);
    }

    @GetMapping("/mycourse")
    public ResponseEntity<?> getMyCourses(HttpServletRequest request) {
        String username = request.getHeader("X-Username");

        HashSet<UserCourseDto> userPurchasedCourse = userService.getUserPurchasedCourse(username);
        return ResponseEntity.ok().body(userPurchasedCourse);
    }

}
