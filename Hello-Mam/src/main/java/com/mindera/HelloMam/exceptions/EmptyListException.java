package com.mindera.HelloMam.exceptions;

public class EmptyListException extends NullPointerException {

    public EmptyListException() {
        super(Messages.EMPTY_LIST);
    }
}
