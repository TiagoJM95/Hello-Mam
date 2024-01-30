package com.mindera.HelloMam.exceptions.media_exceptions;

import static com.mindera.HelloMam.utils.ExceptionMessages.TYPE_NOT_FOUND;

public class TypeNotFoundException extends MediaException{

    public TypeNotFoundException() {
        super(TYPE_NOT_FOUND);
    }
}
