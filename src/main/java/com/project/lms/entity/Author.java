package com.project.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    @CreationTimestamp
    private Date createdDate;

    //This won't be added to the table as we have not provided joinColumn
    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties({"author"})
    private List<Book> bookList;
}
