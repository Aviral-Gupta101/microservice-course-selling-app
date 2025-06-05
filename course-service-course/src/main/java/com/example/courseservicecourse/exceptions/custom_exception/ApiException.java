package com.example.courseservicecourse.exceptions.custom_exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    @Getter
    private final HttpStatus httpStatus;
    public ApiException(HttpStatus status, String message) {
        super(message);
        this.httpStatus = status;
    }
}
