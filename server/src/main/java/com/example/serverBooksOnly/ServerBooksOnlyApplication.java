package com.example.serverBooksOnly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ServerBooksOnlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerBooksOnlyApplication.class, args);
	}

}
