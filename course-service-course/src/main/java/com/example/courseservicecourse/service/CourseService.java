package com.example.courseservicecourse.service;

import com.example.courseservicecourse.dto.RegisterCourseDto;
import com.example.courseservicecourse.entity.Course;
import com.example.courseservicecourse.exceptions.custom_exception.ApiException;
import com.example.courseservicecourse.repo.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepo;

    public CourseService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course getCourseById(Integer id) {
        return courseRepo.findByCourseId(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    public Course registerCourse(RegisterCourseDto dto) {

        Course course = Course.builder()
                .title(dto.getTitle())
                .price(dto.getPrice())
                .build();

        return courseRepo.save(course);
    }

}
