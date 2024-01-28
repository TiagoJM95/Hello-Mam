package com.mindera.HelloMam.exceptions.user_exceptions;

import com.mindera.HelloMam.utils.Messages;

import static com.mindera.HelloMam.utils.Messages.USER_NOT_FOUND;

public class UserNotFoundException extends UserException{
    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }
}
