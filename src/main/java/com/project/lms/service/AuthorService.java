package com.project.lms.service;

import com.project.lms.entity.Author;
import com.project.lms.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepo;
    public Author createAuthor(Author author){
        Author author1=authorRepo.findByEmail(author.getEmail());
        if(author1==null){
            author1= authorRepo.save(author);
        }
        return author1;
    }
}
