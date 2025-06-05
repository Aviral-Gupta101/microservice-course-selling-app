package com.example.courseservicecourse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterCourseDto {

    @NotBlank
    String title;

    @NotNull
    Integer price;
}
