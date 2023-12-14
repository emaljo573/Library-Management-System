package com.project.lms.service;

import com.project.lms.entity.Author;
import com.project.lms.entity.Book;
import com.project.lms.entity.Genre;
import com.project.lms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository bookRepo;

    public void createBook(Book book){
        Author author=book.getAuthor();
        Author authorEntity=authorService.createAuthor(author);
        book.setAuthor(authorEntity);
        bookRepo.save(book);
    }

    public List<Book> getBook(String key, String val) throws Exception {

        switch(key){
            case "id":{
                Optional<Book> book= bookRepo.findById(Integer.parseInt(val));
                return book.map(Arrays::asList).orElseGet(ArrayList::new);
            }
            case "name":
                return bookRepo.findByName(val);
            case "author":
                return bookRepo.findByAuthorName(val);
            case "genre":
                return bookRepo.findByGenre(Genre.valueOf(val));
            default:
                throw new Exception("Not found");
        }
    }
}
