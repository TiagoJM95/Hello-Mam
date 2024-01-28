package com.mindera.HelloMam.exceptions.media_exceptions;

import com.mindera.HelloMam.utils.Messages;

import static com.mindera.HelloMam.utils.Messages.REF_ID_NOT_FOUND;

public class RefIdNotFoundException extends MediaException{

    public RefIdNotFoundException() {
        super(REF_ID_NOT_FOUND);
    }
}
