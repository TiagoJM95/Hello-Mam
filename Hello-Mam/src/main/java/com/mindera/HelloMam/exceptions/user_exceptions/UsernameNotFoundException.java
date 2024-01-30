package com.mindera.HelloMam.exceptions.user_exceptions;

import static com.mindera.HelloMam.utils.ExceptionMessages.USERNAME_NOT_FOUND;

public class UsernameNotFoundException extends UserException{
    public UsernameNotFoundException() {
        super(USERNAME_NOT_FOUND);
    }
}
