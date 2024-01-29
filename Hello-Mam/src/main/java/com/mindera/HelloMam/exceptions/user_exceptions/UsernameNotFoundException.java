package com.mindera.HelloMam.exceptions.user_exceptions;

public class UsernameNotFoundException extends UserException{
    public UsernameNotFoundException() {
        super("This username does not exist.");
    }
}
