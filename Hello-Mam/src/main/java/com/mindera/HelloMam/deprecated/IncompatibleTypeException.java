package com.mindera.HelloMam.deprecated;

import com.mindera.HelloMam.exceptions.media.MediaException;

import static com.mindera.HelloMam.utils.ExceptionMessages.INCOMPATIBLE_TYPE;

public class IncompatibleTypeException extends MediaException {

    public IncompatibleTypeException() {
        super(INCOMPATIBLE_TYPE);
    }
}