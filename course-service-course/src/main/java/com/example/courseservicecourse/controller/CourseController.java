package com.example.courseservicecourse.controller;

import com.example.courseservicecourse.dto.RegisterCourseDto;
import com.example.courseservicecourse.entity.Course;
import com.example.courseservicecourse.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {


    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        List<Course> allCourses = courseService.getAllCourses();
        return ResponseEntity.ok().body(allCourses);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable Integer courseId) {
        Course course = courseService.getCourseById(courseId);
        return ResponseEntity.ok().body(course);
    }

    @PostMapping
    public ResponseEntity<?> registerCourse(@RequestBody RegisterCourseDto dto) {
        Course course = courseService.registerCourse(dto);
        return ResponseEntity.ok().body(course);
    }
    

}
