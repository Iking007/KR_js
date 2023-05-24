package com.example.serverBooksOnly.Controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.serverBooksOnly.Model.Role;
import com.example.serverBooksOnly.Model.User;
import com.example.serverBooksOnly.Repository.UsersRepository;
import com.example.serverBooksOnly.Token.TokenRepository;



@RestController
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UsersRepository usersRepository;
    int elInPage = 20; // Количество элементов на странице


    @GetMapping("/users/{page}")
    @CrossOrigin(origins = "*")
    public String allusers(@PathVariable(value = "page") int id){
        List<User> users = usersRepository.findAll();
        String message;
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        List<User> pageUsers = users.subList(elInPage*id-elInPage, ((users.size() <= elInPage*id) ? users.size() : elInPage*id));
        for (User user : pageUsers){
            JSONObject jsonuser = new JSONObject();
            jsonuser.put("id", user.getId());
            jsonuser.put("name", user.getName());
            jsonuser.put("email", user.getEmail());
            jsonuser.put("role", user.getRole());
            array.put(jsonuser);
            System.out.println(array.toString());
        }
        json.put("users", array);
        message = json.toString();
        return message;
    }

    @PostMapping("/setrole")
    @CrossOrigin(origins = "*")
    public String setRole(@RequestParam Long id, @RequestParam int role, @RequestParam int page){
        User user = usersRepository.findById(id.longValue()).get();
        if (role == 1) {
            user.setRole(Role.USER);
        }
        if (role == 2) { 
            user.setRole(Role.MODER);
        }
        if (role == 3) {
            user.setRole(Role.ADMIN);
        }
        usersRepository.save(user);
        List<User> users = usersRepository.findAll();
        String message;
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        List<User> pageUsers = users.subList(elInPage*page-elInPage, ((users.size() <= elInPage*page) ? users.size() : elInPage*page));
        for (User us : pageUsers){
            JSONObject jsonuser = new JSONObject();
            jsonuser.put("id", us.getId());
            jsonuser.put("name", us.getName());
            jsonuser.put("email", us.getEmail());
            jsonuser.put("role", us.getRole());
            array.put(jsonuser);
            System.out.println(array.toString());
        }
        json.put("users", array);
        message = json.toString();
        return message;
    }
}
