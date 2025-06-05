package com.example.courseservicecourse.repo;

import com.example.courseservicecourse.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseId(Integer courseId);
}
