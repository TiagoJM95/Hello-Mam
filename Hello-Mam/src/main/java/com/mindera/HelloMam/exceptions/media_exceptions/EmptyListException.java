package com.mindera.HelloMam.exceptions.media_exceptions;

import com.mindera.HelloMam.messages.Messages;

public class EmptyListException extends MediaException {

    public EmptyListException() {
        super(Messages.EMPTY_LIST);
    }
}
