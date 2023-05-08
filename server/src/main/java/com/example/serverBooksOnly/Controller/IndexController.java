package com.example.serverBooksOnly.Controller;

import com.example.serverBooksOnly.Model.Book;
import com.example.serverBooksOnly.Model.Role;
import com.example.serverBooksOnly.Model.User;
import com.example.serverBooksOnly.Repository.BooksRepository;

import java.util.Arrays;
import java.util.List;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class IndexController{
    @Autowired
    BooksRepository booksRepository;
    int elInPage = 12; // Количество элементов на странице

    @GetMapping("/")
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
    @GetMapping("/books")
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
    @GetMapping("/books/query")
    public String books(@RequestParam String filter){
        Iterable<Book> books = booksRepository.findByTitle(filter);

        // if (books.toString().isEmpty()) {
        //     return "redirect:/books";
        // }
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
        // model.addAttribute("namePage", "Книги");
        // model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/books/{id}")
    @CrossOrigin(origins = "*")
    public String PageId(@PathVariable(value = "id") int id){
        List<Book> books = booksRepository.findAll();
        String message;
        JSONObject json = new JSONObject();
        json.put("page", id);
        json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
        json.put("books", Arrays.copyOfRange(books.toArray(), elInPage*id-elInPage, ((books.size() <= elInPage*id) ? books.size()-1 : elInPage*id)));
        message = json.toString();
        System.out.println(message);
        return message;
    }
    @GetMapping("/book/{id}")
    @CrossOrigin(origins = "*")
    public String bookId(@PathVariable(value = "id") int id){
        List<Book> book = booksRepository.findById(id);
        String message;
        JSONObject json = new JSONObject();
        json.put("book", book.toArray());
        message = json.toString();
        System.out.println(message);
        return message;
    }
    @GetMapping("/prof")
    public String prof(){
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
        // model.addAttribute("username", user.getEmail());
        return "prof";
    }
    }
