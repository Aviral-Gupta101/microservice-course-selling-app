package com.example.courseserviceuser.client;

import com.example.courseserviceuser.dto.TransactionDto;
import com.example.courseserviceuser.entity.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "course-service-transaction")
public interface TransactionClient {

    @PostMapping("/transaction")
    Transaction saveTransaction(@RequestBody TransactionDto dto);

    @GetMapping("transaction/{userID}")
    List<Transaction> getUserTransaction(@PathVariable("userID") Integer userID);

}
