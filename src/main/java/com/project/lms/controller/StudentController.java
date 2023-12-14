package com.project.lms.controller;

import com.project.lms.dto.CreateStudentRequest;
import com.project.lms.entity.Student;
import com.project.lms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void createStudet(@RequestBody @Valid CreateStudentRequest studentReq){
        studentService.create(studentReq.toStudent());
    }

    @GetMapping("/student")
    public Student fetchStudent(@RequestParam("id") Integer id){
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/student")
    public void deleteStudent(@RequestParam("id") Integer id){
        studentService.deleteStudent(id);
    }

    @PutMapping("/student")
    public void updateStudent(@RequestParam("id") Integer id, @RequestBody @Valid CreateStudentRequest studentReq){
        studentService.updateStudent(id,studentReq);
    }


}
