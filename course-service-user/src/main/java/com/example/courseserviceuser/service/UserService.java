package com.example.courseserviceuser.service;

import com.example.courseserviceuser.client.CourseClient;
import com.example.courseserviceuser.client.TransactionClient;
import com.example.courseserviceuser.dto.TransactionDto;
import com.example.courseserviceuser.dto.UserCourseDto;
import com.example.courseserviceuser.entity.Course;
import com.example.courseserviceuser.entity.Transaction;
import com.example.courseserviceuser.entity.User;
import com.example.courseserviceuser.exceptions.custom_exception.ApiException;
import com.example.courseserviceuser.repo.UserRepository;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CourseClient courseClient;
    private final TransactionClient transactionClient;


    public UserService(UserRepository userRepository, CourseClient courseClient, TransactionClient transactionClient) {
        this.userRepository = userRepository;
        this.courseClient = courseClient;
        this.transactionClient = transactionClient;
    }

    public User findByUsername(String username) {
        return userRepository.findUsersByUsername(username).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Username not found"));
    }

    public User registerUser(String username, String password) {

        Optional<User> foundUser = userRepository.findUsersByUsername(username);

        if(foundUser.isPresent())
            throw new ApiException(HttpStatus.CONFLICT, "Username already exists");

        User newUser = User.builder()
                .username(username)
                .password(password)
                .build();

        return userRepository.save(newUser);
    }

    public Transaction buyCourse(String username, Integer courseId) {

        try {

            User foundUser = findByUsername(username);
            Course course = courseClient.getCourse(courseId);
            TransactionDto transactionDto = new TransactionDto();

            transactionDto.setCourseId(courseId);
            transactionDto.setUserId(foundUser.getId());
            transactionDto.setPrice(course.getPrice());


            return transactionClient.saveTransaction(transactionDto);

        } catch (FeignException e) {
            if(e.status() == HttpStatus.NOT_FOUND.value()){
                throw new ApiException(HttpStatus.NOT_FOUND, "Course not found");
            }
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public HashSet<UserCourseDto> getUserPurchasedCourse(String username){

        User foundUser = findByUsername(username);
        List<Transaction> userTransaction = transactionClient.getUserTransaction(foundUser.getId());
        List<Course> allCourse = courseClient.getAllCourse();

        HashMap<Integer, Course> courseMap = new HashMap<>();

        allCourse.forEach(course -> {
            courseMap.put(course.getCourseId(), course);
        });

        HashSet<UserCourseDto> userCourseSet = new HashSet<>();

        userTransaction.forEach(item -> {

            int courseId = item.getCourseId();
            Course course = courseMap.get(courseId);

            UserCourseDto dto = UserCourseDto.builder()
                    .price(course.getPrice())
                    .purchaseDate(item.getTransactionDate())
                    .course(course)
                    .build();

            userCourseSet.add(dto);
        });

        return userCourseSet;
    }

}
