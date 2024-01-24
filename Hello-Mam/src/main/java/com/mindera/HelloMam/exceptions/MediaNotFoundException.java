package com.mindera.HelloMam.exceptions;

public class MediaNotFoundException extends IllegalStateException{

    public MediaNotFoundException() {
        super(Messages.MEDIA_NOT_FOUND);
    }
}
