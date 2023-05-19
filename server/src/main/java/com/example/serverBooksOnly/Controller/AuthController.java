package com.example.serverBooksOnly.Controller;

import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
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
        String message;
        token = token.substring(7,token.length());
        User user = tokenRepository.findByToken(token).get().getUser();
        System.out.println(user.getFavorites());
        json.put("books", user.getFavorites());
        message = json.toString();
        return message;
    }

    @PostMapping("/newfavorite")
    @CrossOrigin(origins = "*")
    public void newfavorites(@RequestHeader("Authorization") String token, @RequestBody FavoriteRequest request){
        token = token.substring(7,token.length());
        User user = tokenRepository.findByToken(token).get().getUser();
        user.addFavorite(booksRepository.findById(request.getBook_id()).get());
        usersRepository.save(user);
        return;
    }
    @PostMapping("/delfavorite")
    @CrossOrigin(origins = "*")
    public void delfavorites(@RequestHeader("Authorization") String token, @RequestBody FavoriteRequest request){
        token = token.substring(7,token.length());
        User user = tokenRepository.findByToken(token).get().getUser();
        user.delFavorite(booksRepository.findById(request.getBook_id()).get());
        usersRepository.save(user);
        return;
    }

    @GetMapping("/auth/book/{id}")
    @CrossOrigin(origins = "*")
    public String bookId(@PathVariable(value = "id") long id, @RequestHeader("Authorization") String token){
        List<Book> book = booksRepository.findById(id);
        JSONObject json = new JSONObject();
        String message = "";        
        token = token.substring(7,token.length());
        User user = tokenRepository.findByToken(token).get().getUser();
        // user.getFavorites().contains(book);
        json.put("book", book.toArray());
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
       
        //System.out.println(message);
        return message;
    }
}
