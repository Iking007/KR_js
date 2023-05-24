package com.example.serverBooksOnly.security;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.serverBooksOnly.Model.Role;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CsrfFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                // .disable()
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers("/", "/query", "/books/**", "/book/**", "/reg", "/login", "/genres", "/authors")// Указанное здесь будет доступно для всех пользователей
                    .permitAll()
                    .requestMatchers("/allbooks", "/users").hasRole(Role.ADMIN.name())
                    .requestMatchers("/addbook", "/addgenre", "/addauthor", "/delauthor", "/delgenre", "/delbook").hasAnyRole(Role.ADMIN.name(), Role.MODER.name())
                    .anyRequest()
                    .authenticated()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
