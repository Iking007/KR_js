package com.example.serverBooksOnly.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private String title;
    private Long author_id;
    private Long genre_id;
    private String download;
    private String str;
    private String img;
    // private Genre genre;
    // private Author author;
}
