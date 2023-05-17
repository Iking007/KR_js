// package com.example.serverBooksOnly.Controller;

// import com.example.serverBooksOnly.Model.User;
// import com.example.serverBooksOnly.Repository.UsersRepository;

// import org.json.JSONObject;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;


// @RestController
// public class LoginController {
//     @Autowired
//     private UsersRepository usersRepository;

//     @GetMapping("/login")
//     //@CrossOrigin(origins = "*")
//     public String login(@AuthenticationPrincipal User user){
//         JSONObject json = new JSONObject();
//         json.put("key", false);
//         json.put("error", false);
//         return json.toString();

//     }
//     @PostMapping("/login")
//     //@CrossOrigin(origins = "*")
//     public String bookAdd(@RequestParam String username, @RequestParam String password, Model model) {
//         User user = new User(username, password);
//         User userFromBD = usersRepository.findByEmail(user.getEmail()).get();
//         String password1 = userFromBD.getPassword();
//         System.out.println(password+ " требуемый:" + password1);
//         JSONObject json = new JSONObject();
//         json.put("key", false);
//         if(password == password1) {json.put("error", false);}else json.put("error", true);
//         return json.toString();
//     }
    
// }
