package com.mindera.HelloMam.exceptions.rating_exceptions;

import com.mindera.HelloMam.messages.Messages;

public class RatingNotFoundException extends IllegalStateException{

    public RatingNotFoundException() {
        super(Messages.RATING_NOT_FOUND);
    }
}
