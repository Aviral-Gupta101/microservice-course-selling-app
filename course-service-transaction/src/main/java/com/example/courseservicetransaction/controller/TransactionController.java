package com.example.courseservicetransaction.controller;

import com.example.courseservicetransaction.dto.TransactionDto;
import com.example.courseservicetransaction.entity.Transaction;
import com.example.courseservicetransaction.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {


    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<?> saveTransaction(@Valid  @RequestBody TransactionDto dto) {

        Transaction res = transactionService.saveTransaction(dto.getCourseId(), dto.getUserId(), dto.getPrice());
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<?> getUserTransaction(@PathVariable Integer userID) {

        List<Transaction> res = transactionService.getAllUserTransactions(userID);
        return ResponseEntity.ok().body(res);
    }

}
