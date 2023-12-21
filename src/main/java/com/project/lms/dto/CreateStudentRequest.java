package com.project.lms.dto;

import com.project.lms.entity.AuthUser;
import com.project.lms.entity.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotNull
    private Integer age;

    @NotBlank
    String username;

    @NotBlank
    String password;


    public Student toStudent(){
        return Student.builder()
                .name(this.name)
                .email(this.email)
                .age(this.age)
                .authUser(
                        AuthUser.builder()
                                .userName(this.username)
                                .password(this.password)
                                .build()
                )
                .build();
    }
}
