package com.mindera.services.interfaces;

import com.mindera.dtos.MovieGetDto;
import com.mindera.entities.Movie;
import com.mindera.exceptions.movie.MovieNotFoundException;

import java.util.List;

public interface MovieService {
    Movie findByMongoId(String id) throws MovieNotFoundException;
    Movie findByTmdbId(Integer id) throws MovieNotFoundException;
    MovieGetDto getMovieById(String id) throws MovieNotFoundException;
    List<MovieGetDto> getMoviesByTitle(String title) throws MovieNotFoundException;
    List<MovieGetDto> getAllMovies();
    MovieGetDto create(Movie movie);
}