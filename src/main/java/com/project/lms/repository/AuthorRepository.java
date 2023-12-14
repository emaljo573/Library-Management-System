package com.project.lms.repository;

import com.project.lms.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    Author findByEmail(String email);
}
