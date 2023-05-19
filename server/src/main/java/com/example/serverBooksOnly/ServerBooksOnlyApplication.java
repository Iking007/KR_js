package com.example.serverBooksOnly;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.serverBooksOnly.Model.Role;
import com.example.serverBooksOnly.Repository.UsersRepository;
import com.example.serverBooksOnly.Requests.RegisterRequest;
import com.example.serverBooksOnly.auth.AuthenticationService;

@SpringBootApplication
@EnableWebMvc
public class ServerBooksOnlyApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServerBooksOnlyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.name("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(Role.ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var moder = RegisterRequest.builder()
					.name("Moder")
					.email("moder@mail.com")
					.password("password")
					.role(Role.MODER)
					.build();
			System.out.println("Manager token: " + service.register(moder).getAccessToken());
			// if (repository.findByEmail("admin@mail.com").get() == null) {
			// 	var admin = RegisterRequest.builder()
			// 			.name("Admin")
			// 			.email("admin@mail.com")
			// 			.password("password")
			// 			.role(Role.ADMIN)
			// 			.build();
			// 	System.out.println("Admin token: " + service.register(admin).getToken());
			// 	var moder = RegisterRequest.builder()
			// 		.name("Moder")
			// 		.email("moder@mail.com")
			// 		.password("password")
			// 		.role(Role.MODER)
			// 		.build();
			// 	System.out.println("Moder token: " + service.register(moder).getToken());
			// }
			// else{
			// 	var admin = AuthenticationRequest.builder()
			// 			.email("admin@mail.com")
			// 			.password("password")
			// 			.build();
			// 	var moder = AuthenticationRequest.builder()
			// 			.email("moder@mail.com")
			// 			.password("password")
			// 			.build();
			// 	System.out.println("Admin уже зарегистрирован, token: " + service.authenticate(admin).getToken());
			// 	System.out.println("Moder уже зарегистрирован,  token: " + service.authenticate(moder).getToken());
			// }
		};
	}

}
