package com.example.serverBooksOnly.Controller;

import org.json.JSONObject;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.serverBooksOnly.Model.User;
import com.example.serverBooksOnly.Repository.UsersRepository;
import com.example.serverBooksOnly.Token.TokenRepository;
import com.example.serverBooksOnly.auth.AuthenticationRequest;
import com.example.serverBooksOnly.auth.AuthenticationResponse;
import com.example.serverBooksOnly.auth.AuthenticationService;
import com.example.serverBooksOnly.auth.RegisterRequest;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;

    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UsersRepository usersRepository;

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
}
