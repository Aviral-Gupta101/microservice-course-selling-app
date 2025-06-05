package com.example.courseservicetransaction.service;

import com.example.courseservicetransaction.entity.Transaction;
import com.example.courseservicetransaction.repo.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepo;

    public TransactionService(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public List<Transaction> getAllUserTransactions(Integer userId) {
        return transactionRepo.findAllByUserId(userId);
    }

    public Transaction saveTransaction(Integer courseId, Integer userId, Integer amount) {

        Transaction transaction = Transaction.builder()
                .courseId(courseId)
                .userId(userId)
                .price(amount)
                .build();

        return transactionRepo.save(transaction);
    }

}
