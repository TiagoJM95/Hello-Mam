package com.mindera.HelloMam.exceptions.media_exceptions;

import com.mindera.HelloMam.utils.Messages;

import static com.mindera.HelloMam.utils.Messages.TYPE_NOT_FOUND;

public class TypeNotFoundException extends MediaException{

    public TypeNotFoundException() {
        super(TYPE_NOT_FOUND);
    }
}
