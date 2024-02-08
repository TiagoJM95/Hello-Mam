package com.mindera.HelloMam.exceptions.rating;

public class DuplicateRatingException extends RatingException{
    public DuplicateRatingException(String message) {
        super(message);
    }
}