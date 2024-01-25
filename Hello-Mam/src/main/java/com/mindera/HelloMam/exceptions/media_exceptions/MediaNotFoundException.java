package com.mindera.HelloMam.exceptions.media_exceptions;

import com.mindera.HelloMam.messages.Messages;

public class MediaNotFoundException extends IllegalStateException{

    public MediaNotFoundException() {
        super(Messages.MEDIA_NOT_FOUND);
    }
}
