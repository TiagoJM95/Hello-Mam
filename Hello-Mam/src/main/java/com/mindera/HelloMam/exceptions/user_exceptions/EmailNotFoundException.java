package com.mindera.HelloMam.exceptions.user_exceptions;

import com.mindera.HelloMam.utils.Messages;

import static com.mindera.HelloMam.utils.Messages.EMAIL_NOT_FOUND;

public class EmailNotFoundException extends UserException{
    public EmailNotFoundException() {
        super(EMAIL_NOT_FOUND);
    }
}
