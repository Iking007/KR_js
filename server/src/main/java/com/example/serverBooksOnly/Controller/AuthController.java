package com.example.serverBooksOnly.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.serverBooksOnly.auth.AuthenticationRequest;
import com.example.serverBooksOnly.auth.AuthenticationResponse;
import com.example.serverBooksOnly.auth.AuthenticationService;
import com.example.serverBooksOnly.auth.RegisterRequest;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;


    @PostMapping("/reg")
    @CrossOrigin(origins = "*")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
