package com.mindera.HelloMam.aspects;

import com.mindera.HelloMam.exceptions.media.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.exceptions.rating.RatingNotFoundException;
import com.mindera.HelloMam.exceptions.user.DuplicateEmailException;
import com.mindera.HelloMam.exceptions.user.DuplicateUsernameException;
import com.mindera.HelloMam.exceptions.user.EmailNotFoundException;
import com.mindera.HelloMam.exceptions.user.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.mindera.HelloMam.utils.ExceptionMessages.ERROR_OCCURRED;

@Component
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> genericExceptionHandler(Exception exception) {
        logger.error("Unknown exception: " + exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ERROR_OCCURRED + " "  + exception.getMessage());
    }


   @ExceptionHandler(value = {UserNotFoundException.class,
            EmailNotFoundException.class, RatingNotFoundException.class, MediaTypeNotFoundException.class,
            RefIdNotFoundException.class, MediaNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(Exception exception) {
        logger.error("Known exception: " + exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }


    @ExceptionHandler(value = {DuplicateEmailException.class, DuplicateUsernameException.class})
    public ResponseEntity<String> handleDuplicateException(Exception exception) {
        logger.error("Known exception: " + exception);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
