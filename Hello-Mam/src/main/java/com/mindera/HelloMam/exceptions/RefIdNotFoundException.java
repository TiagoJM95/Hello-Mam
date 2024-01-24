package com.mindera.HelloMam.exceptions;

public class RefIdNotFoundException extends IllegalStateException{

    public RefIdNotFoundException() {
        super(Messages.REF_ID_NOT_FOUND);
    }
}
