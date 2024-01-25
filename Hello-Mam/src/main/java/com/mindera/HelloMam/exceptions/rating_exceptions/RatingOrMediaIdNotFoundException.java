package com.mindera.HelloMam.exceptions.rating_exceptions;

import com.mindera.HelloMam.messages.Messages;

public class RatingOrMediaIdNotFoundException extends NullPointerException{
    public RatingOrMediaIdNotFoundException() {
        super(Messages.RATING_OR_MEDIA_NOT_FOUND);
    }
}
