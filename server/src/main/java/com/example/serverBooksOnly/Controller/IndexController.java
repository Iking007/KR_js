package com.example.serverBooksOnly.Controller;

import com.example.serverBooksOnly.Model.Author;
import com.example.serverBooksOnly.Model.Book;
import com.example.serverBooksOnly.Model.Genre;
import com.example.serverBooksOnly.Model.Role;
import com.example.serverBooksOnly.Model.User;
import com.example.serverBooksOnly.Repository.AuthorRepository;
import com.example.serverBooksOnly.Repository.BooksRepository;
import com.example.serverBooksOnly.Repository.GenreRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class IndexController{
    @Autowired
    BooksRepository booksRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    AuthorRepository authorRepository;
    int elInPage = 12; // Количество элементов на странице

    @GetMapping("/")
    @CrossOrigin(origins = "*")
    public String index(){
        // model.addAttribute("namePage", "Главная");
        // if(user == null){
        //     model.addAttribute("pr", null);
        // }
        // else if( Objects.equals(user.getRole(), Collections.singleton(Role.USER))){
        //     model.addAttribute("pr", 0);
        // }
        // else if(!Objects.equals(user.getRole(), Collections.singleton(Role.ADMIN))){
        //     model.addAttribute("pr", 1);
        // }
        // else {
        //     model.addAttribute("pr", 2);
        // }
        return "index";
    }
    
    @GetMapping("/allbooks")
    @CrossOrigin(origins = "*")
    public String books(){
        List<Book> books = booksRepository.findAll();
        String message;
        JSONObject json = new JSONObject();

        json.put("books", books);
        message = json.toString();
        System.out.println(message);
        return message;
    }
    @GetMapping("/query")
    @CrossOrigin(origins = "*")
    public String booksQueru(@RequestParam(required = false) String filter,
            @RequestParam(required = false) Long genre_id, @RequestParam(required = false) Long author_id, @RequestParam int page){
        String message = "";
        System.out.println(genre_id);
        JSONObject json = new JSONObject();
        json.put("page", page);
        if (filter != null){
            //System.out.println(filter);
            List<Book> books = booksRepository.searchByTitle(filter);
            if (books.size() != 0){
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                json.put("books", books);
                message = json.toString();
            }
            else{
                books = booksRepository.findAll();
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                json.put("books", books);
                json.put("query", "false");
                message = json.toString();
            }
        }
        else if (genre_id != null){
            List<Book> books = booksRepository.searchByGenre(genreRepository.findById(genre_id.longValue()));
                if (books.size() != 0){
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                json.put("books", books);
                message = json.toString();
            }
            else{
                books = booksRepository.findAll();
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                json.put("books", books);
                json.put("query", "false");
                message = json.toString();
            }
        }
        else if (author_id != null){
            List<Book> books = booksRepository.searchByAuthor(authorRepository.findById(author_id.longValue()));
            json.put("page", page);
            if (books.size() != 0){
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                json.put("books", books);
                message = json.toString();
            }
            else{
                books = booksRepository.findAll();
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                json.put("books", books);
                json.put("query", "false");
                message = json.toString();
            }
        }
        //System.out.println(message);
        return message;
    }
    
    @GetMapping("/books/{id}")
    @CrossOrigin(origins = "*")
    public String PageId(@PathVariable(value = "id") int id){
        List<Book> books = booksRepository.findAll();
        String message;
        JSONObject json = new JSONObject();
        json.put("page", id);
        json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
        json.put("books", Arrays.copyOfRange(books.toArray(), elInPage*id-elInPage, ((books.size() <= elInPage*id) ? books.size() : elInPage*id)));
        message = json.toString();
        //System.out.println(message);
        return message;
    }
    
    @GetMapping("/book/{id}")
    @CrossOrigin(origins = "*")
    public String bookId(@PathVariable(value = "id") long id){
        List<Book> book = booksRepository.findById(id);
        String message;
        JSONObject json = new JSONObject();
        json.put("book", book.toArray());
        message = json.toString();
        //System.out.println(message);
        return message;
    }
    
    @GetMapping("/genres")
    @CrossOrigin(origins = "*") 
    public String Genres(){
        List<Genre> genres = genreRepository.findAll();
        String message;
        JSONObject json = new JSONObject();
        json.put("genres", genres.toArray());
        message = json.toString();
        System.out.println(message);
        return message;
    }
    
    @GetMapping("/authors")
    @CrossOrigin(origins = "*") 
    public String Authors(){
        List<Author> authors = authorRepository.findAll();
        String message;
        JSONObject json = new JSONObject();
        json.put("authors", authors.toArray());
        message = json.toString();
        System.out.println(message);
        return message;
    }
    
    @GetMapping("/prof")
    public String prof(){
        
        return "prof";
    }
    }
