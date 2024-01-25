package com.mindera.entity;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Movie {
    private String title;
    private String director;
    private int releaseYear;

    private String genre;

    private String plot;
    private String language;
    private String country;
    private String rating;

    public Movie(String title, String director, String genre, String rating) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.rating = rating;
    }
}



