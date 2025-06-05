package com.example.courseserviceuser.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDto {

    @NotNull
    Integer userId;

    @NotNull
    Integer courseId;

    @NotNull
    Integer price;
}
