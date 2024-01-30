package com.mindera.HelloMam.exceptions.media_exceptions;

import static com.mindera.HelloMam.utils.ExceptionMessages.INCOMPATIBLE_TYPE;

public class IncompatibleTypeException extends MediaException{

    public IncompatibleTypeException() {
        super(INCOMPATIBLE_TYPE);
    }
}
