package com.example.serverBooksOnly.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;///< Идентификатор автора

    private String surname;///< Фамилия автора
    private String name;///< Имя автора
    private String middle_name;///< Отчество/второе имя автора
    private Date date_of_birth;///< Дата рождения автора 
    @Column(columnDefinition="text")
    private String description;///< Краткая биография автора
}