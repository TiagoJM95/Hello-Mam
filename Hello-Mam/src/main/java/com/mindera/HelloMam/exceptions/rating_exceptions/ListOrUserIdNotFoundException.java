package com.mindera.HelloMam.exceptions.rating_exceptions;

import com.mindera.HelloMam.messages.Messages;

public class ListOrUserIdNotFoundException extends NullPointerException{

    public ListOrUserIdNotFoundException() {
        super(Messages.LIST_OR_USER_NOT_FOUND);
    }
}
