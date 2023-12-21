package com.project.lms.dto;

import com.project.lms.entity.Admin;
import com.project.lms.entity.AuthUser;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAdminReq {

    @NotBlank
    private String name;
    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Admin toAdmin(){
        return Admin.builder()
                .name(this.name)
                .email(this.email)
                .authUser(
                        AuthUser.builder().
                                userName(this.username).
                                password(this.password).
                                build()
                )
                .build();
    }
}
