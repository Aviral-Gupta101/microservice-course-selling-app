package com.example.courseserviceauth.clients;

import com.example.courseserviceauth.dto.SignupSigninDto;
import com.example.courseserviceauth.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "course-service-user") // or use service discovery name
public interface UserClient {

    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username);

    @PostMapping("/users/save")
    public User registerUser(@RequestBody SignupSigninDto dto);
}
