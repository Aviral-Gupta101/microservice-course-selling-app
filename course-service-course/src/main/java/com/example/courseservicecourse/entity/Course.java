package com.example.courseservicecourse.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Course")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer courseId;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    Integer price;
}
