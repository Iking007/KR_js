package com.example.serverBooksOnly.Model;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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

    public Book() {
    }

    public Book(String title, String str, Author writer, Genre genre) {
        this.title = title;
        this.str = str;
        /**
         * \todo Дописать, когда будет добавлена таблица писателей: this.id_author = (long) 0; this.id_genre = (long) 0;
         */
    }
    public Book(String title, String str) {
        this.title = title;
        this.str = str;
    }
}


