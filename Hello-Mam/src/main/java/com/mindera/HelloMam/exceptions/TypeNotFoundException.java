package com.mindera.HelloMam.exceptions;

public class TypeNotFoundException extends IllegalStateException{

    public TypeNotFoundException() {
        super(Messages.TYPE_NOT_FOUND);
    }
}
