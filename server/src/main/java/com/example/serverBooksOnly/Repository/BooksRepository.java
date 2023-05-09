package com.example.serverBooksOnly.Repository;

import com.example.serverBooksOnly.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();
    List<Book> findById(long id);
    List<Book> findByTitle(String title);

    @Query("""
        SELECT b FROM Book b WHERE LOWER(b.title) LIKE CONCAT('%', LOWER(:title), '%')
            """)
    List<Book> searchByTitle(@Param("title") String title);

    // @Query("""
    //         select b from Book b
    //         where upper(b.title) like upper(concat(?1, '%')) or upper(b.writer) like upper(concat(?2, '%')) or upper(b.title) like upper(concat('%', ?3)) or upper(b.writer) like upper(concat('%', ?4))""")
    // List<Book> search(String title, String writer, String title1, String writer1);
    
    




}

