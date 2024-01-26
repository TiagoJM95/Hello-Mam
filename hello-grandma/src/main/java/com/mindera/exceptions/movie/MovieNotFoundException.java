package com.mindera.exceptions.movie;

public class MovieNotFoundException extends MovieException{
    public MovieNotFoundException(String message) {
        super(message);
    }
}