package com.mindera.HelloMam.exceptions;

public class MediaFoundException extends IllegalStateException {
    public MediaFoundException() {
        super(Messages.MEDIA_FOUND);
    }
}
