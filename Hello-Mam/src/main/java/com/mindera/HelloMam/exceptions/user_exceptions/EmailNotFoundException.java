package com.mindera.HelloMam.exceptions.user_exceptions;

import static com.mindera.HelloMam.utils.ExceptionMessages.EMAIL_NOT_FOUND;

public class EmailNotFoundException extends UserException{
    public EmailNotFoundException() {
        super(EMAIL_NOT_FOUND);
    }
}
