package com.example.serverBooksOnly.Repository;


import com.example.serverBooksOnly.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    User findByEmail(String email);
}
