package com.project.lms.repository;

import com.project.lms.entity.Book;
import com.project.lms.entity.Student;
import com.project.lms.entity.Transaction;
import com.project.lms.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(
            Student student, Book book, TransactionType transactionType
            );

    Transaction findByTransactionId(String txnId);
}
