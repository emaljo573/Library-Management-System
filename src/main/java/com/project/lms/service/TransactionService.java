package com.project.lms.service;

import com.project.lms.dto.CreateTransactionReq;
import com.project.lms.entity.*;
import com.project.lms.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    @Autowired
    AdminService adminService;

    @Value("${max-books}")
    Integer maxBooks;

    @Value("${max-days}")
    Integer maxDay;

    @Autowired
    TransactionRepository transactionRepo;

    public String initiateTxn(CreateTransactionReq req,Integer adminId) throws Exception {
        if(req.getTxnType().equals(TransactionType.ISSUE)){
            return issuance(req,adminId);
        }
        return returnBook(req,adminId);
    }

    private String issuance(CreateTransactionReq req,Integer adminId) throws Exception {
        Student student=studentService.getStudentById(req.getStudentId());
        Admin admin=adminService.findById(adminId);
        List<Book> bookList =bookService.getBook("id",String.valueOf(req.getBookId()));
        Book book=bookList!=null && !bookList.isEmpty() ? bookList.get(0) : null;
        if(book==null || student==null || admin==null){
            throw new Exception("Invalid Request");
        }
        if(book.getStudent()!=null){
            throw  new Exception("Book is unavailable");
        }
        if(student.getBookList().size()>=maxBooks){
            throw  new Exception("Maximum book threshold reached");
        }
        Transaction txnEntity=new Transaction();
        try{
            txnEntity=Transaction.builder()
                    .transactionId(UUID.randomUUID().toString())
                    .student(student)
                    .book(book)
                    .admin(admin)
                    .transactionType(req.getTxnType())
                    .status(TransactionStatus.PENDING)
                    .build();

            transactionRepo.save(txnEntity);
            book.setStudent(student);
            bookService.createBook(book);
            txnEntity.setStatus(TransactionStatus.SUCCESS);
        }catch (Exception e){
            txnEntity.setStatus(TransactionStatus.FAILURE);
        }finally {
            transactionRepo.save(txnEntity);
        }
        return txnEntity.getTransactionId();

    }

    private String returnBook(CreateTransactionReq req,Integer adminId) throws Exception {
        Student student=studentService.getStudentById(req.getStudentId());
        Admin admin=adminService.findById(adminId);
        List<Book> bookList =bookService.getBook("id",String.valueOf(req.getBookId()));
        Book book=bookList!=null && !bookList.isEmpty() ? bookList.get(0) : null;
        if(book==null || student==null || admin==null){
            throw new Exception("Invalid Request");
        }
        if(book.getStudent()==null){
            throw new Exception("Book is not assigned to anyone");
        }
        if(!Objects.equals(book.getStudent().getId(), student.getId())){
            throw new Exception("Book is not issued to the requested student");
        }
        Transaction issuanceTxn=transactionRepo.findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(student, book,TransactionType.ISSUE);
        if(issuanceTxn==null){
            throw new Exception("Book is not issued to the requested student");
        }

        Transaction txnEntity=new Transaction();
        try{
            Integer fine=calculateFine(issuanceTxn.getCreatedOn());
            txnEntity=Transaction.builder()
                    .transactionId(UUID.randomUUID().toString())
                    .student(student)
                    .book(book)
                    .admin(admin)
                    .transactionType(req.getTxnType())
                    .status(TransactionStatus.PENDING)
                    .fine(fine)
                    .build();
            transactionRepo.save(txnEntity);
            if(fine==0){
                book.setStudent(null);
                bookService.createBook(book);
                txnEntity.setStatus(TransactionStatus.SUCCESS);
            }

        }catch(Exception e){
            txnEntity.setStatus(TransactionStatus.FAILURE);
        }finally {
            transactionRepo.save(txnEntity);
        }
        return txnEntity.getTransactionId();
    }

    private Integer calculateFine(Date createdTime){
        long issuanceTimeInMills=createdTime.getTime();
        long currentTime=System.currentTimeMillis();
        long diff=currentTime-issuanceTimeInMills;
        long days= TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
        if(days>maxDay){
            return (int)(days-maxDay);
        }
        return 0;

    }

    public void payFine(Integer amt, Integer studentId, String txnId) throws Exception {
        Transaction transaction=transactionRepo.findByTransactionId(txnId);
        Book book=transaction.getBook();
        if(Objects.equals(transaction.getFine(), amt) && book.getStudent()!=null && book.getStudent().getId()==studentId){
            transaction.setStatus(TransactionStatus.SUCCESS);
            book.setStudent(null);
            bookService.createBook(book);
            transactionRepo.save(transaction);
        }else{
            throw new Exception("Invalid request");
        }
    }
}
