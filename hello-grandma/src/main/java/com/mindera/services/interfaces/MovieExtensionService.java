package com.mindera.services.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindera.dtos.MovieGetDto;
import com.mindera.entities.Movie;
import com.mindera.entities.MovieExtension;
import com.mindera.exceptions.movie.InvalidGenreException;

import java.util.List;

public interface MovieExtensionService {
    Movie getMovieDetailsByTmdbId(String tmdbId) throws JsonProcessingException;
    List<MovieExtension.MovieResponse> getMovieRecommendation(Integer movieId) throws JsonProcessingException;
    List<MovieExtension.MovieResponse> discoverMovies(String genres) throws JsonProcessingException;
}