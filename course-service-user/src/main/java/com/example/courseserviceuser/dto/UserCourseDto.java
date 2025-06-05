package com.example.courseserviceuser.dto;

import com.example.courseserviceuser.entity.Course;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserCourseDto {

    Course course;
    Integer price;
    LocalDate purchaseDate;
}
