package com.project.lms.repository;

import com.project.lms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Transactional
    @Modifying
    @Query("update Student s set s.name=:name,s.email=:email,s.age=:age where s.id=:id")
    void updateStudent(Integer id,String name,String email,Integer age);
}
