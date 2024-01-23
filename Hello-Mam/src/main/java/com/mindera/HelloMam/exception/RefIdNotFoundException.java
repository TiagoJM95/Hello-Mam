package com.mindera.HelloMam.exception;

public class RefIdNotFoundException extends IllegalStateException{

    public RefIdNotFoundException() {
        super(Messages.REF_ID_NOT_FOUND);
    }
}
