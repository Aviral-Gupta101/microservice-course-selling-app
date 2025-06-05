package com.example.courseservicetransaction.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Transaction")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer transactionId;

    @Column(nullable = false)
    Integer courseId;

    @Column(nullable = false)
    Integer userId;

    @Column(nullable = false)
    Integer price;

    @Builder.Default
    LocalDate transactionDate = LocalDate.now();

}
