package com.mindera.HelloMam.exceptions.user;

import static com.mindera.HelloMam.utils.ExceptionMessages.DUPLICATE_USERNAME;

public class DuplicateUsernameException extends UserException{
    public DuplicateUsernameException() {
        super(DUPLICATE_USERNAME);
    }
}
