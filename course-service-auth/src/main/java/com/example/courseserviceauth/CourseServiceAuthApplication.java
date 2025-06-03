package com.example.courseserviceauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CourseServiceAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceAuthApplication.class, args);
    }

}
