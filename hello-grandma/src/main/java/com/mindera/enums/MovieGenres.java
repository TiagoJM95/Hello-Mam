package com.mindera.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor

public enum MovieGenres {
    ACTION(28, "Action"),
    ADVENTURE(12, "Adventure"),
    ANIMATION(16, "Animation"),
    COMEDY(35, "Comedy"),
    CRIME(80, "Crime"),
    DOCUMENTARY(99, "Documentary"),
    DRAMA(18, "Drama"),
    FAMILY(10751, "Family"),
    FANTASY(14, "Fantasy"),
    HISTORY(36, "History"),
    HORROR(27, "Horror"),
    MUSIC(10402, "Music"),
    MYSTERY(9648, "Mystery"),
    ROMANCE(10749, "Romance"),
    SCIENCE_FICTION(878, "Science Fiction"),
    TV_MOVIE(10770, "TV Movie"),
    THRILLER(53, "Thriller"),
    WAR(10752, "War"),
    WESTERN(37, "Western");

    private final int id;
    private final String name;

    public static Optional<MovieGenres> getMovieGenreByName(String name) {
        for (MovieGenres movieGenres : MovieGenres.values()) {
            if (movieGenres.getName().replace(" ", "").equalsIgnoreCase(name)) {
                return Optional.of(movieGenres);
            }
        }
        return Optional.empty();
    }
}