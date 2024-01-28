package com.mindera.HelloMam.exceptions.media_exceptions;

import com.mindera.HelloMam.utils.Messages;

import static com.mindera.HelloMam.utils.Messages.MEDIA_NOT_FOUND;

public class MediaNotFoundException extends MediaException {

    public MediaNotFoundException() {
        super(MEDIA_NOT_FOUND);
    }
}
