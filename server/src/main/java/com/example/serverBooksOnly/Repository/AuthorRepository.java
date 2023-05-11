package com.example.serverBooksOnly.Repository;


import com.example.serverBooksOnly.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAll();
    Author findById(long id);
}
