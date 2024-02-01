package com.mindera.services.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindera.dtos.MovieGetDto;
import com.mindera.entities.Movie;
import com.mindera.entities.MovieExtension;
import com.mindera.exceptions.movie.InvalidGenreException;
import com.mindera.exceptions.movie.MovieNotFoundException;

import java.util.List;

public interface MovieService {
    Movie findByMongoId(String id) throws MovieNotFoundException;
    Movie findByTmdbId(Long id) throws MovieNotFoundException;
    MovieGetDto getMovieById(String id) throws MovieNotFoundException;
    List<MovieGetDto> getMoviesByTitle(String title) throws MovieNotFoundException;
    List<MovieGetDto> getAllMovies();
    Movie getMovieDetailsByTmdbId(String tmdbId) throws JsonProcessingException;
    List<MovieGetDto> getMovieRecommendation(Integer movieId) throws JsonProcessingException;
    List<MovieGetDto> discoverMovies(String genres) throws InvalidGenreException, JsonProcessingException;
    void create(Movie movie);
    String convertGenreStringToGenreId(String genres) throws InvalidGenreException;
    List<Movie> fromMovieExtensionListToMovieList(List<MovieExtension.MovieResponse> movieExtensionList) throws JsonProcessingException;
}