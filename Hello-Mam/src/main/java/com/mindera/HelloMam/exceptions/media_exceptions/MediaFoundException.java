package com.mindera.HelloMam.exceptions.media_exceptions;

import com.mindera.HelloMam.messages.Messages;

public class MediaFoundException extends IllegalStateException {
    public MediaFoundException() {
        super(Messages.MEDIA_FOUND);
    }
}
