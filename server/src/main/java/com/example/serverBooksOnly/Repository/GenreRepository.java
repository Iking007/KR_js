package com.example.serverBooksOnly.Repository;

import com.example.serverBooksOnly.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findAll();
    Genre findById(long id);
}
