package com.mindera.HelloMam.exception;

public class MediaFoundException extends IllegalStateException {
    public MediaFoundException() {
        super(Messages.MEDIA_FOUND);
    }
}
