package com.example.serverBooksOnly.Model;
import lombok.Data;
import java.sql.Date;

import jakarta.persistence.*;



@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;///< Идентификатор автора

    private String surname_author;///< Фамилия автора
    private String name_author;///< Имя автора
    private String middle_name_author;///< Отчество/второе имя автора
    private Date date_of_birth;///< Дата рождения автора 
    @Column(columnDefinition="text")
    private String description_author;///< Краткая биография автора
}