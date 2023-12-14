package com.project.lms.controller;

import com.project.lms.dto.CreateBookReq;
import com.project.lms.entity.Book;
import com.project.lms.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;
    @PostMapping("/book")
    public void createBook(@RequestBody @Valid CreateBookReq req){
        bookService.createBook(req.toBook());
    }

    @GetMapping("/book")
    public List<Book> getBook(@RequestParam("key") String key, @RequestParam("value") String value) throws Exception{
        return bookService.getBook(key,value);
    }
}
