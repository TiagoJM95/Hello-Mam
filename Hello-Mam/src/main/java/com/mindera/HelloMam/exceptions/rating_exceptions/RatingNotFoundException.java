package com.mindera.HelloMam.exceptions.rating_exceptions;

import static com.mindera.HelloMam.utils.Messages.*;

public class RatingNotFoundException extends RatingException{

    public RatingNotFoundException() {
        super(RATING_NOT_FOUND);
    }
}