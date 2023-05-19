package com.example.serverBooksOnly.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

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
    

    // public Book(String title, Author author, Genre genre, String img, String download, String str) {
    //     this.title = title;
    //     this.str = str;
    //     this.author = author;
    //     this.genre = genre;
    //     this.img = img;
    //     this.download = download;
    // }
    // public Book(String title, String str) {
    //     this.title = title;
    //     this.str = str;
    // }
}



