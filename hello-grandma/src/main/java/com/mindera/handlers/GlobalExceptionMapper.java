package com.mindera.handlers;

import com.mindera.exceptions.movie.MovieException;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.exceptions.game.GameException;
import com.mindera.exceptions.game.GameNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static com.mindera.util.Messages.*;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    private Response handleMovieException(MovieException movieException) {
        if(movieException instanceof MovieNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(movieException.getMessage())
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(MOVIE_RELATED_ERROR + movieException.getMessage())
                .build();
    }
    private Response handleVideogameException(GameException gameException) {
        if(gameException instanceof GameNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(gameException.getMessage())
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(VIDEO_GAME_RELATED_ERROR + gameException.getMessage())
                .build();
    }
    @Override
    public Response toResponse(Exception exception) {
        if(exception instanceof MovieException) {
            return handleMovieException((MovieException) exception);
        }
        if (exception instanceof GameException) {
            return handleVideogameException((GameException) exception);
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .build();
    }
}