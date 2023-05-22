package com.example.serverBooksOnly.Controller;

import com.example.serverBooksOnly.Model.Author;
import com.example.serverBooksOnly.Model.Book;
import com.example.serverBooksOnly.Model.Genre;
import com.example.serverBooksOnly.Repository.AuthorRepository;
import com.example.serverBooksOnly.Repository.BooksRepository;
import com.example.serverBooksOnly.Repository.GenreRepository;
import com.example.serverBooksOnly.Repository.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    UsersRepository usersRepository;
    int elInPage = 12; // Количество элементов на странице

    @GetMapping("/")
    @CrossOrigin(origins = "*")
    public String index(){
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
                // JSONArray arrayFavotites = new JSONArray();
                JSONArray array = new JSONArray();
                List<Book> pageBooks = books.subList(elInPage*page-elInPage, ((books.size() <= elInPage*page) ? books.size() : elInPage*page));
                json.put("page", page);
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                for (Book book : pageBooks){
                    JSONObject jsonBook = new JSONObject();
                    // Book book = pageBooks.;
                    jsonBook.put("id", book.getId());
                    jsonBook.put("title", book.getTitle());
                    // jsonBook.put("author", book.getAuthor());
                    // jsonBook.put("genre", book.getGenre());
                    jsonBook.put("img", book.getImg());
                    jsonBook.put("download", book.getDownload());
                    array.put(jsonBook);
                    // jsonBook.clear();
                    System.out.println(array.toString());
                }
                json.put("books", array);
                message = json.toString();
            }
            else{
                books = booksRepository.findAll();
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                // JSONArray arrayFavotites = new JSONArray();
                JSONArray array = new JSONArray();
                List<Book> pageBooks = books.subList(elInPage*page-elInPage, ((books.size() <= elInPage*page) ? books.size() : elInPage*page));
                json.put("page", page);
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                for (Book book : pageBooks){
                    JSONObject jsonBook = new JSONObject();
                    // Book book = pageBooks.;
                    jsonBook.put("id", book.getId());
                    jsonBook.put("title", book.getTitle());
                    // jsonBook.put("author", book.getAuthor());
                    // jsonBook.put("genre", book.getGenre());
                    jsonBook.put("img", book.getImg());
                    jsonBook.put("download", book.getDownload());
                    array.put(jsonBook);
                    // jsonBook.clear();
                    System.out.println(array.toString());
                }
                json.put("books", array);
                json.put("query", "false");
                message = json.toString();
            }
        }
        else if (genre_id != null){
            List<Book> books = booksRepository.searchByGenre(genreRepository.findById(genre_id.longValue()));
                if (books.size() != 0){
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                // JSONArray arrayFavotites = new JSONArray();
                JSONArray array = new JSONArray();
                List<Book> pageBooks = books.subList(elInPage*page-elInPage, ((books.size() <= elInPage*page) ? books.size() : elInPage*page));
                json.put("page", page);
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                for (Book book : pageBooks){
                    JSONObject jsonBook = new JSONObject();
                    // Book book = pageBooks.;
                    jsonBook.put("id", book.getId());
                    jsonBook.put("title", book.getTitle());
                    // jsonBook.put("author", book.getAuthor());
                    // jsonBook.put("genre", book.getGenre());
                    jsonBook.put("img", book.getImg());
                    jsonBook.put("download", book.getDownload());
                    array.put(jsonBook);
                    // jsonBook.clear();
                    System.out.println(array.toString());
                }
                json.put("books", array);
                message = json.toString();
            }
            else{
                books = booksRepository.findAll();
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                // JSONArray arrayFavotites = new JSONArray();
                JSONArray array = new JSONArray();
                List<Book> pageBooks = books.subList(elInPage*page-elInPage, ((books.size() <= elInPage*page) ? books.size() : elInPage*page));
                json.put("page", page);
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                for (Book book : pageBooks){
                    JSONObject jsonBook = new JSONObject();
                    // Book book = pageBooks.;
                    jsonBook.put("id", book.getId());
                    jsonBook.put("title", book.getTitle());
                    // jsonBook.put("author", book.getAuthor());
                    // jsonBook.put("genre", book.getGenre());
                    jsonBook.put("img", book.getImg());
                    jsonBook.put("download", book.getDownload());
                    array.put(jsonBook);
                    // jsonBook.clear();
                    System.out.println(array.toString());
                }
                json.put("books", array);
                json.put("query", "false");
                message = json.toString();
            }
        }
        else if (author_id != null){
            List<Book> books = booksRepository.searchByAuthor(authorRepository.findById(author_id.longValue()));
            json.put("page", page);
            if (books.size() != 0){
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                // JSONArray arrayFavotites = new JSONArray();
                JSONArray array = new JSONArray();
                List<Book> pageBooks = books.subList(elInPage*page-elInPage, ((books.size() <= elInPage*page) ? books.size() : elInPage*page));
                json.put("page", page);
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                for (Book book : pageBooks){
                    JSONObject jsonBook = new JSONObject();
                    // Book book = pageBooks.;
                    jsonBook.put("id", book.getId());
                    jsonBook.put("title", book.getTitle());
                    // jsonBook.put("author", book.getAuthor());
                    // jsonBook.put("genre", book.getGenre());
                    jsonBook.put("img", book.getImg());
                    jsonBook.put("download", book.getDownload());
                    array.put(jsonBook);
                    // jsonBook.clear();
                    System.out.println(array.toString());
                }
                json.put("books", array);
                message = json.toString();
            }
            else{
                books = booksRepository.findAll();
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                
                // JSONArray arrayFavotites = new JSONArray();
                JSONArray array = new JSONArray();
                List<Book> pageBooks = books.subList(elInPage*page-elInPage, ((books.size() <= elInPage*page) ? books.size() : elInPage*page));
                json.put("page", page);
                json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
                for (Book book : pageBooks){
                    JSONObject jsonBook = new JSONObject();
                    // Book book = pageBooks.;
                    jsonBook.put("id", book.getId());
                    jsonBook.put("title", book.getTitle());
                    // jsonBook.put("author", book.getAuthor());
                    // jsonBook.put("genre", book.getGenre());
                    jsonBook.put("img", book.getImg());
                    jsonBook.put("download", book.getDownload());
                    array.put(jsonBook);
                    // jsonBook.clear();
                    System.out.println(array.toString());
                }
                json.put("books", array);
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
        // JSONObject jsonBook = new JSONObject();
        // JSONArray arrayFavotites = new JSONArray();
        JSONArray array = new JSONArray();
        List<Book> pageBooks = books.subList(elInPage*id-elInPage, ((books.size() <= elInPage*id) ? books.size() : elInPage*id));
        json.put("page", id);
        json.put("maxPage", ((books.size()%elInPage == 0 )? books.size()/elInPage : books.size()/elInPage + 1));
        for (Book book : pageBooks){
            JSONObject jsonBook = new JSONObject();
            // Book book = pageBooks.;
            jsonBook.put("id", book.getId());
            jsonBook.put("title", book.getTitle());
            // jsonBook.put("author", book.getAuthor());
            // jsonBook.put("genre", book.getGenre());
            jsonBook.put("img", book.getImg());
            jsonBook.put("download", book.getDownload());
            array.put(jsonBook);
            // jsonBook.clear();
            System.out.println(array.toString());
        }
        json.put("books", array);
        message = json.toString();
        //System.out.println(message);
        return message;
    }
    
    @GetMapping("/book/{id}")
    @CrossOrigin(origins = "*")
    public String bookId(@PathVariable(value = "id") long id) throws JsonProcessingException, JSONException{
        Book book = booksRepository.findById(id);
        String message;
        JSONObject json = new JSONObject();
        JSONObject jsonBook = new JSONObject();
        jsonBook.put("id", book.getId());
        jsonBook.put("title", book.getTitle());
        ObjectMapper mapper = new ObjectMapper();
        jsonBook.put("author", new JSONObject(mapper.writeValueAsString(book.getAuthor())));
        jsonBook.put("genre",  new JSONObject(mapper.writeValueAsString(book.getGenre())));
        jsonBook.put("img", book.getImg());
        jsonBook.put("download", book.getDownload());
        json.put("book", jsonBook);
        message = json.toString();
        System.out.println(message);
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
}
