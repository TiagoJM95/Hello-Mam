package com.mindera.HelloMam.aspects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class Error {
    Date timestamp;
    String message;
    String method;
    String path;
}