package com.mindera.services.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindera.entities.MovieExtension;

import java.util.List;

public interface MovieExtensionService {
    List<MovieExtension.MovieResponse> getMovieRecommendation(String movieId) throws JsonProcessingException;
}