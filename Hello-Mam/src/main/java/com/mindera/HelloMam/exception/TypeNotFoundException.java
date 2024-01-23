package com.mindera.HelloMam.exception;

public class TypeNotFoundException extends IllegalStateException{

    public TypeNotFoundException() {
        super(Messages.TYPE_NOT_FOUND);
    }
}
