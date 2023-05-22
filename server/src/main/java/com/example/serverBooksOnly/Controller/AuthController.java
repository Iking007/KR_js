package com.example.serverBooksOnly.Controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.serverBooksOnly.Model.Book;
import com.example.serverBooksOnly.Model.User;
import com.example.serverBooksOnly.Repository.BooksRepository;
import com.example.serverBooksOnly.Repository.UsersRepository;
import com.example.serverBooksOnly.Requests.AuthenticationRequest;
import com.example.serverBooksOnly.Requests.FavoriteRequest;
import com.example.serverBooksOnly.Requests.RegisterRequest;
import com.example.serverBooksOnly.Token.TokenRepository;
import com.example.serverBooksOnly.auth.AuthenticationResponse;
import com.example.serverBooksOnly.auth.AuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;

    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    BooksRepository booksRepository;

    @PostMapping("/reg")
    @CrossOrigin(origins = "*")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        if (usersRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/prof")
    @CrossOrigin(origins = "*")
    public String profile(@RequestHeader("Authorization") String token){
        JSONObject json = new JSONObject();
        String message;
        //  System.out.println(token);
        token = token.substring(7,token.length());
        //  System.out.println(token);
        User user = tokenRepository.findByToken(token).get().getUser();
        json.put("name", user.getName());
        json.put("role", user.getRole().name());
        //  System.out.println(user.getName());
        message = json.toString();
        return message;
    }

    @GetMapping("/favorites")
    @CrossOrigin(origins = "*")
    public String favorites(@RequestHeader("Authorization") String token){
        JSONObject json = new JSONObject();
        
        // JSONArray arrayFavotites = new JSONArray();
        JSONArray array = new JSONArray();
        String message;
        token = token.substring(7,token.length());
        User user = tokenRepository.findByToken(token).get().getUser();
        for (int i = 0; i < user.getFavorites().size(); i++){
            JSONObject jsonBook = new JSONObject();
            Book book = user.getFavorites().get(i);
            jsonBook.put("id", book.getId());
            jsonBook.put("title", book.getTitle());
            // jsonBook.put("author", book.getAuthor());
            // jsonBook.put("genre", book.getGenre());
            jsonBook.put("img", book.getImg());
            jsonBook.put("download", book.getDownload());
            array.put(jsonBook);
            System.out.println(array.toString());
        }
        json.put("books", array);
        message = json.toString();
        System.out.println(message);
        return message;
    }

    @PostMapping("/newfavorite")
    @CrossOrigin(origins = "*")
    public void newfavorites(@RequestHeader("Authorization") String token, @RequestBody FavoriteRequest request){
        System.out.println(request.toString());
        token = token.substring(7,token.length());
        User user = tokenRepository.findByToken(token).get().getUser();
        user.addFavorite(booksRepository.findById(request.getBook_id().longValue()));
        usersRepository.save(user);
        return;
    }
    @PostMapping("/delfavorite")
    @CrossOrigin(origins = "*")
    public void delfavorites(@RequestHeader("Authorization") String token, @RequestBody FavoriteRequest request){
        token = token.substring(7,token.length());
        User user = tokenRepository.findByToken(token).get().getUser();
        user.delFavorite(booksRepository.findById(request.getBook_id().longValue()));
        usersRepository.save(user);
        return;
    }

    @GetMapping("/auth/book/{id}")
    @CrossOrigin(origins = "*")
    public String bookId(@PathVariable(value = "id") long id, @RequestHeader("Authorization") String token) throws JsonProcessingException, JSONException{
        Book book = booksRepository.findById(id);
        JSONObject json = new JSONObject();
        JSONObject jsonBook = new JSONObject();
        String message = "";        
        token = token.substring(7,token.length());
        User user = tokenRepository.findByToken(token).get().getUser();
        // user.getFavorites().contains(book);
        jsonBook.put("id", book.getId());
        jsonBook.put("title", book.getTitle());
        ObjectMapper mapper = new ObjectMapper();
        jsonBook.put("author", new JSONObject(mapper.writeValueAsString(book.getAuthor())));
        jsonBook.put("genre",  new JSONObject(mapper.writeValueAsString(book.getGenre())));
        jsonBook.put("img", book.getImg());
        jsonBook.put("download", book.getDownload());
        jsonBook.put("str", book.getStr());
        json.put("book", jsonBook);
        Iterator<Book> favorites = user.getFavorites().iterator();
        while(favorites.hasNext()){
            //json.put("favorite", true);
            //System.out.println(favorites(token));
            if(favorites.next().getId() == id){
                json.put("favorite", true);
            }
        }
        if(!json.has("favorite")) json.put("favorite", false);
        
        message = json.toString();
       
        System.out.println(message);
        return message;
    }
}
