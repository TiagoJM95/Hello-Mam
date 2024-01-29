package com.mindera.HelloMam.exceptions.user_exceptions;

import static com.mindera.HelloMam.utils.Messages.USERNAME_FOUND;

public class DuplicateUsernameException extends UserException{
    public DuplicateUsernameException() {
        super(USERNAME_FOUND);
    }
}
