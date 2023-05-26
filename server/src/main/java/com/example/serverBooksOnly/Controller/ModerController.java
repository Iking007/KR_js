package com.example.serverBooksOnly.Controller;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
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
import com.example.serverBooksOnly.Model.User;
import com.example.serverBooksOnly.Repository.AuthorRepository;
import com.example.serverBooksOnly.Repository.BooksRepository;
import com.example.serverBooksOnly.Repository.GenreRepository;
import com.example.serverBooksOnly.Repository.UsersRepository;
import com.example.serverBooksOnly.Requests.AuthorRequest;
import com.example.serverBooksOnly.Requests.BookRequest;
import com.example.serverBooksOnly.Requests.FavoriteRequest;
import com.example.serverBooksOnly.Requests.GenreRequest;
import com.example.serverBooksOnly.Token.TokenRepository;

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
            .img(request.getImg())
            .str(request.getStr())
            .build();
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
        Book book = booksRepository.findById(book_id).get();
        Iterator<User> usersIterator = book.getUsers().iterator();
        while(usersIterator.hasNext()) {
            User user = usersIterator.next();
            user.delFavorite(book);
            usersRepository.save(user);
        }
        booksRepository.deleteById(book_id);
        return;
    }

    @GetMapping("/delauthor")
    @CrossOrigin(origins = "*")
    public String delauthor(
        @RequestParam Long author_id
    ){
        booksRepository.deleteAllInBatch(booksRepository.findAllByAuthor(authorRepository.findById(author_id).get()));
        authorRepository.deleteById(author_id);
        List<Author> authors = authorRepository.findAll();
        String message;
        JSONObject json = new JSONObject();
        json.put("authors", authors.toArray());
        message = json.toString();
        System.out.println(message);
        return message;
    }

    @GetMapping("/delgenre")
    @CrossOrigin(origins = "*")
    public String delgenre(
        @RequestParam Long genre_id
        ){
            booksRepository.deleteAllInBatch(booksRepository.findAllByGenre(genreRepository.findById(genre_id).get()));
            genreRepository.deleteById(genre_id);
            List<Genre> genres = genreRepository.findAll();
            String message;
            JSONObject json = new JSONObject();
            json.put("genres", genres.toArray());
            message = json.toString();
            System.out.println(message);
            return message;
        }
}
