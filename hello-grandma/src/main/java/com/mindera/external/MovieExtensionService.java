package com.mindera.external;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface MovieExtensionService {
    List<MovieExtension.MovieResponse> getMovieRecommendation(String movieId) throws JsonProcessingException;
}