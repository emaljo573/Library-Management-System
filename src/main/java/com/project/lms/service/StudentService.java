package com.project.lms.service;

import com.project.lms.dto.CreateStudentRequest;
import com.project.lms.entity.AuthUser;
import com.project.lms.entity.Student;
import com.project.lms.repository.StudentRepository;
import com.project.lms.utils.AuthUserService;
import com.project.lms.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AuthUserService authUserService;

    public void create(Student student){
        AuthUser authUser=student.getAuthUser();
        authUser=authUserService.saveAuthUser(authUser, Constants.STUDENT_USER);
        student.setAuthUser(authUser);
        studentRepository.save(student);
    }

    public Student getStudentById(Integer id){
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(Integer id) {
         studentRepository.deleteById(id);
    }

    public void updateStudent(Integer id, CreateStudentRequest studentReq) {
        studentRepository.updateStudent(id,studentReq.getName(),studentReq.getEmail(),studentReq.getAge());
    }
}
