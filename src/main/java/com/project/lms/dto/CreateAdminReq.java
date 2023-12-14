package com.project.lms.dto;

import com.project.lms.entity.Admin;
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

    public Admin toAdmin(){
        return Admin.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }
}
