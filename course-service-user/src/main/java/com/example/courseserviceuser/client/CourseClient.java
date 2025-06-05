package com.example.courseserviceuser.client;

import com.example.courseserviceuser.entity.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "course-service-course")
public interface CourseClient {

    @GetMapping("/course/{courseId}")
    Course getCourse(@PathVariable Integer courseId);

    @GetMapping("/course")
    List<Course> getAllCourse();
}
