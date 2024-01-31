package com.mindera.HelloMam.exceptions.media;

import static com.mindera.HelloMam.utils.ExceptionMessages.REF_ID_NOT_FOUND;

public class RefIdNotFoundException extends MediaException{

    public RefIdNotFoundException() {
        super(REF_ID_NOT_FOUND);
    }
}