package com.example.serverBooksOnly.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;///< Идентификатор книги 
    
    private String title;///< Название книги
    private String img;///< Путь к файлу обложки книги
    private String download;///< Путь к файлу/ссылка для скачивания книги с веб-сайта
    @Column(columnDefinition = "TEXT")
    private String str;///< Текстовое описание книги
    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author; ///< Идентификатор автора книги
    @ManyToOne
    @JoinColumn(name = "id_genre")
    private Genre genre; ///< Идентификатор жанра книги

    @ManyToMany(mappedBy = "favorites")
    private List<User> users;
}



