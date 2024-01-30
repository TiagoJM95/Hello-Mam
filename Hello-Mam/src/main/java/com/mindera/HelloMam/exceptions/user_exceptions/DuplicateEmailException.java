package com.mindera.HelloMam.exceptions.user_exceptions;

import static com.mindera.HelloMam.utils.ExceptionMessages.DUPLICATE_EMAIL;

public class DuplicateEmailException extends UserException{
    public DuplicateEmailException() {
        super(DUPLICATE_EMAIL);
    }
}
