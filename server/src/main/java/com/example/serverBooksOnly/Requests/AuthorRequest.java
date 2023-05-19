package com.example.serverBooksOnly.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {
    private String name;
    private String surname;
    private String middle_name;
    private String str;
    
}
