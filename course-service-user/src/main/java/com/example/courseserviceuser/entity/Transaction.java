package com.example.courseserviceuser.entity;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Transaction {

    Integer transactionId;
    Integer courseId;
    Integer userId;
    Integer price;
    LocalDate transactionDate = LocalDate.now();

}
