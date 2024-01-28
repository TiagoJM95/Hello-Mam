package com.mindera.HelloMam.aspects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@Builder
@Getter
@Setter
public class Error extends ResponseEntity<Error> {

    Date timestamp;
    String message;
    String method;
    String path;

}
