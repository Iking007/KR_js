package com.example.serverBooksOnly.Model;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
