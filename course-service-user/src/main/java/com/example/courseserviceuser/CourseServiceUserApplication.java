package com.example.courseserviceuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class CourseServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceUserApplication.class, args);
    }

}
