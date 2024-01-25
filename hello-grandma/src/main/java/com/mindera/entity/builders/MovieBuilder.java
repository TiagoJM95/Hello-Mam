package com.mindera.entity.builders;

import com.mindera.entity.Movie;

public class MovieBuilder {
    private String title;

    private String director;

    private String genre;

    private String rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    public Movie getResult() {
       return new Movie(title,director,genre,rating);
    }
}
