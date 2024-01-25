package com.mindera.HelloMam.exceptions.media_exceptions;

import com.mindera.HelloMam.messages.Messages;

public class RefIdNotFoundException extends IllegalStateException{

    public RefIdNotFoundException() {
        super(Messages.REF_ID_NOT_FOUND);
    }
}
