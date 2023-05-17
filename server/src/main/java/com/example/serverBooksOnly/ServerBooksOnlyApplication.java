package com.example.serverBooksOnly;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.serverBooksOnly.Model.Role;
import com.example.serverBooksOnly.auth.AuthenticationService;
import com.example.serverBooksOnly.auth.RegisterRequest;

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
			System.out.println("Admin token: " + service.register(admin).getToken());

			var moder = RegisterRequest.builder()
					.name("Moder")
					.email("moder@mail.com")
					.password("password")
					.role(Role.MODER)
					.build();
			System.out.println("Manager token: " + service.register(moder).getToken());

		};
	}

}
