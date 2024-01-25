package com.mindera.HelloMam.exceptions.media_exceptions;

import com.mindera.HelloMam.messages.Messages;

public class TypeNotFoundException extends IllegalStateException{

    public TypeNotFoundException() {
        super(Messages.TYPE_NOT_FOUND);
    }
}
