// package com.example.serverBooksOnly.Model;

// import jakarta.persistence.*;

// import lombok.Data;

// @Data
// @Entity
// public class Favorites {
//     @Id
//     @GeneratedValue(strategy = GenerationType.TABLE)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "book_id")
//     private Book book;

//     @ManyToOne
//     @JoinColumn(name = "user_id")
//     private User user;
// }
