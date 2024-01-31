package com.mindera.HelloMam.exceptions.user;

import static com.mindera.HelloMam.utils.ExceptionMessages.USER_NOT_FOUND;

public class UserNotFoundException extends UserException{
    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }
}
