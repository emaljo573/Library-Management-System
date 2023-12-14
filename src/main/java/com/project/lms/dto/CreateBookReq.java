package com.project.lms.dto;

import com.project.lms.entity.Author;
import com.project.lms.entity.Book;
import com.project.lms.entity.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookReq {

    @NotBlank
    private String name;
    @NotNull
    private Genre genre;
    @NotBlank
    private String authorName;

    @NotBlank
    private String authorEmail;

    public Book toBook(){
        return Book.builder()
                .name(this.name)
                .genre(this.genre)
                .author(Author.builder()
                        .email(this.authorEmail)
                        .name(this.authorName)
                        .build())
                .build();
    }
}
