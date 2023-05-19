package com.example.serverBooksOnly.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.serverBooksOnly.Model.Author;
import com.example.serverBooksOnly.Model.Book;
import com.example.serverBooksOnly.Model.Genre;
import com.example.serverBooksOnly.Repository.AuthorRepository;
import com.example.serverBooksOnly.Repository.BooksRepository;
import com.example.serverBooksOnly.Repository.GenreRepository;
import com.example.serverBooksOnly.Repository.UsersRepository;
import com.example.serverBooksOnly.Requests.AuthorRequest;
import com.example.serverBooksOnly.Requests.BookRequest;
import com.example.serverBooksOnly.Requests.GenreRequest;
import com.example.serverBooksOnly.Token.TokenRepository;
import com.example.serverBooksOnly.auth.AuthenticationService;

@RestController
@RequiredArgsConstructor
public class ModerController {
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    BooksRepository booksRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;

    @PostMapping("/addbook")
    @CrossOrigin(origins = "*")
    public void addbook(
            @RequestBody BookRequest request
    ){
        Author author = new Author();
        Genre genre = new Genre();
        if (request.getAuthor_id() != null){
            author = authorRepository.findById(request.getAuthor_id()).get();
        }
        if (request.getGenre_id() != null){
            genre = genreRepository.findById(request.getGenre_id()).get();
        }
        Book book = Book.builder()
            .title(request.getTitle())
            .author(author)
            .genre(genre)
            .download(request.getDownload())
            .str(request.getStr())
            .build();
        // Book book = new Book(request.getTitle(), request.getAuthor(), request.getGenre(),
        //     request.getImg(), request.getDownload(), request.getStr());
        System.out.println(book);
        booksRepository.save(book);
        return;
    }

    @PostMapping("/addauthor")
    @CrossOrigin(origins = "*")
    public void addauthor(
            @RequestBody AuthorRequest request
    ){
        Author author = Author.builder()
            .name(request.getName())
            .surname(request.getSurname())
            .middle_name(request.getMiddle_name())
            .description(request.getStr())
            .build();
        authorRepository.save(author);
        return;
    }

    @PostMapping("/addgenre")
    @CrossOrigin(origins = "*")
    public void addgenre(
            @RequestBody GenreRequest request
    ){
        Genre genre = Genre.builder()
           .name(request.getName())
           .build();
        genreRepository.save(genre);
        return;
    }
    
    @GetMapping("/delbook")
    @CrossOrigin(origins = "*")
    public void delbook(
            @RequestParam Long book_id
    ){

        booksRepository.deleteById(book_id);
        return;
    }

    @GetMapping("/delauthor")
    @CrossOrigin(origins = "*")
    public void delauthor(
        @RequestParam Long author_id
    ){
        authorRepository.deleteById(author_id);
        return;
    }

    @GetMapping("/delgenre")
    @CrossOrigin(origins = "*")
    public void delgenre(
        @RequestParam Long genre_id
        ){
            booksRepository.deleteAllInBatch(booksRepository.findAllByGenre(genreRepository.findById(genre_id).get()));
            genreRepository.deleteById(genre_id);
            return;
        }
}
