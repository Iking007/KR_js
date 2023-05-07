package com.example.serverBooksOnly.Controller;

import com.example.serverBooksOnly.Model.Book;
import com.example.serverBooksOnly.Model.Role;
import com.example.serverBooksOnly.Model.User;
import com.example.serverBooksOnly.Repository.BooksRepository;

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
        // model.addAttribute("namePage", "Книги");
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
        List<Book> books = booksRepository.findAll();
        // model.addAttribute("books", books);
        String message;
        JSONObject json = new JSONObject();
        // json.put("page", "1");

        // JSONArray array = new JSONArray();
        // JSONObject item = new JSONObject();
        // item.put("information", "test");
        // item.put("id", 3);
        // item.put("name", "course1");
        // array.put(item);

        // json.put("course", array);
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
    public String bookId(@PathVariable(value = "id") long id){
        Iterable<Book> book = booksRepository.findById(id);
        // if (book == null){
        //     model.addAttribute("namePage", "404");
        //     return "index";
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
        // model.addAttribute("namePage", "Книга");
        // model.addAttribute("book", book);
        return "book";
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
