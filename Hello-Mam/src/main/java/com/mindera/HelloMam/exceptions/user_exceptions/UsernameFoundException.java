package com.mindera.HelloMam.exceptions.user_exceptions;

import static com.mindera.HelloMam.utils.Messages.USERNAME_FOUND;

public class UsernameFoundException extends UserException{
    public UsernameFoundException() {
        super(USERNAME_FOUND);
    }
}
