package com.mindera.HelloMam.exceptions.media_exceptions;

import static com.mindera.HelloMam.utils.ExceptionMessages.MEDIA_NOT_FOUND;

public class MediaNotFoundException extends MediaException {

    public MediaNotFoundException() {
        super(MEDIA_NOT_FOUND);
    }
}
