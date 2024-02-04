/*
package com.mindera.handlers;


import com.mindera.exceptions.movie.MovieException;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.deprecated.music.MusicException;
import com.mindera.deprecated.music.MusicNotFoundException;
import com.mindera.exceptions.videogame.VideogameException;
import com.mindera.exceptions.videogame.VideogameNotFoundException;
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

    private Response handleMusicException(MusicException musicException) {
        if(musicException instanceof MusicNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(musicException.getMessage())
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(MUSIC_RELATED_ERROR + musicException.getMessage())
                .build();
    }

    private Response handleVideogameException(VideogameException videogameException) {
        if(videogameException instanceof VideogameNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(videogameException.getMessage())
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(VIDEO_GAME_RELATED_ERROR + videogameException.getMessage())
                .build();
    }
    @Override
    public Response toResponse(Exception exception) {
        if(exception instanceof MovieException) {
            return handleMovieException((MovieException) exception);
        }
        if (exception instanceof MusicException) {
            return handleMusicException((MusicException) exception);
        }
        if (exception instanceof VideogameException) {
            return handleVideogameException((VideogameException) exception);
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .build();
    }
}

*/
