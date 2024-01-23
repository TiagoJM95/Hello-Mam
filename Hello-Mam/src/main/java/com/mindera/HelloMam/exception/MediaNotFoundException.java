package com.mindera.HelloMam.exception;

public class MediaNotFoundException extends IllegalStateException{

    public MediaNotFoundException() {
        super(Messages.MEDIA_NOT_FOUND);
    }
}
