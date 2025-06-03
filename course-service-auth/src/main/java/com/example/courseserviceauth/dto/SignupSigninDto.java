package com.example.courseserviceauth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupSigninDto {

    @NotBlank(message = "Username cannot be empty")
    String username;

    @NotBlank(message = "Password cannot be empty")
    String password;
}
