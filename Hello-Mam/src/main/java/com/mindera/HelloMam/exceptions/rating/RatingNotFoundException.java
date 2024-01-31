package com.mindera.HelloMam.exceptions.rating;

import static com.mindera.HelloMam.utils.ExceptionMessages.*;

public class RatingNotFoundException extends RatingException{

    public RatingNotFoundException() {
        super(RATING_NOT_FOUND);
    }
}