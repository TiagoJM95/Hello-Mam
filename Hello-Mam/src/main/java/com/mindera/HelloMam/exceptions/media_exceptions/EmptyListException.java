package com.mindera.HelloMam.exceptions.media_exceptions;

import com.mindera.HelloMam.messages.Messages;

public class EmptyListException extends NullPointerException {

    public EmptyListException() {
        super(Messages.EMPTY_LIST);
    }
}
