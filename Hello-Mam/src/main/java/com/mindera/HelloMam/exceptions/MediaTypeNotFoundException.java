package com.mindera.HelloMam.exceptions;

import static com.mindera.HelloMam.utils.ExceptionMessages.TYPE_NOT_FOUND;

public class MediaTypeNotFoundException extends Exception {
    public MediaTypeNotFoundException() {
        super(TYPE_NOT_FOUND);
    }
}