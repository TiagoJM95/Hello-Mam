package com.mindera.HelloMam.aspects;

import com.mindera.HelloMam.exceptions.media.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.exceptions.rating.DuplicateRatingException;
import com.mindera.HelloMam.exceptions.rating.RatingNotFoundException;
import com.mindera.HelloMam.exceptions.user.DuplicateEmailException;
import com.mindera.HelloMam.exceptions.user.DuplicateUsernameException;
import com.mindera.HelloMam.exceptions.user.EmailNotFoundException;
import com.mindera.HelloMam.exceptions.user.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

import static com.mindera.HelloMam.utils.ExceptionMessages.ERROR_OCCURRED;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error genericExceptionHandler(Exception exception, HttpServletRequest request) {
        return Error.builder()
                .timestamp(new Date())
                .message(ERROR_OCCURRED + exception.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error genericNotValidExceptionHandler(Exception exception, HttpServletRequest request) {
        return Error.builder()
                .timestamp(new Date())
                .message(ERROR_OCCURRED + exception.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();
    }

   @ExceptionHandler(value = {UserNotFoundException.class,
            EmailNotFoundException.class, RatingNotFoundException.class, MediaTypeNotFoundException.class,
            RefIdNotFoundException.class, MediaNotFoundException.class})
   @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Error handleNotFoundException(Exception exception, HttpServletRequest request) {
        return Error.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();
    }


    @ExceptionHandler(value = {DuplicateEmailException.class, DuplicateUsernameException.class, DuplicateRatingException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public Error handleDuplicateException(Exception exception, HttpServletRequest request) {
        return Error.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .method(request.getMethod())
                .path(request.getRequestURI())
                .build();
    }
}
