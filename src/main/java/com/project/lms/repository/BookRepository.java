package com.project.lms.repository;

import com.project.lms.entity.Book;
import com.project.lms.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

   List<Book> findByName(String name);

   List<Book> findByGenre(Genre genre);

   List<Book> findByAuthorName(String authorName);

}
