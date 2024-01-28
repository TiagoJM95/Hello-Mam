package com.mindera.HelloMam.exceptions.user_exceptions;

import static com.mindera.HelloMam.utils.Messages.EMAIL_FOUND;

public class EmailFoundException extends UserException{
    public EmailFoundException() {
        super(EMAIL_FOUND);
    }
}
