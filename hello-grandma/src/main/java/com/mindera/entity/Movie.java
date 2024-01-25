package com.mindera.entity;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Movie {
    private Long id;
    private String title;
    private String director;
    private int releaseYear;
    private String genre;
    private String plot;
    private String language;
    private String country;
    private String rating;
}