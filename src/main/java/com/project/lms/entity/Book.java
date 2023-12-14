package com.project.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(value=EnumType.STRING)
    private Genre genre;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @ManyToOne //First part comes from the class we are in and 2nd the mapped class
    @JoinColumn
   // @JsonIgnoreProperties({"bookList"})
    private Author author;

    @ManyToOne
    @JoinColumn
   // @JsonIgnoreProperties({"bookList"})
    private Student student;

    @OneToMany(mappedBy = "book")
    private List<Transaction> transactionList;
}
