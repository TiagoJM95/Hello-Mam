package com.mindera.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindera.dtos.MovieGetDto;
import com.mindera.entities.Movie;
import com.mindera.entities.MovieExtension;
import com.mindera.enums.MovieGenres;
import com.mindera.exceptions.movie.InvalidGenreException;
import com.mindera.repositories.MovieExtensionRepository;
import com.mindera.services.interfaces.MovieExtensionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

import static com.mindera.enums.MovieGenres.getMovieGenreByName;
import static com.mindera.util.Keys.ACCEPT_HEADER;
import static com.mindera.util.Keys.API_KEY;

@ApplicationScoped
public class MovieExtensionServiceImpl implements MovieExtensionService {

    @Inject
    @RestClient
    MovieExtensionRepository movieExtensionRepository;

    @Inject
    ObjectMapper mapper;

    @Override
    public Movie getMovieDetailsByTmdbId(String tmdbId) throws JsonProcessingException {
        String json = movieExtensionRepository.getMovieById(tmdbId, ACCEPT_HEADER, API_KEY);
        return mapper.readValue(json, Movie.class);
    }

    @Override
    public List<MovieExtension.MovieResponse> getMovieRecommendation(Integer movieId) throws JsonProcessingException {
        String json = movieExtensionRepository.getMovieRecommendation(movieId, ACCEPT_HEADER, API_KEY);
        return mapper.readValue(json, MovieExtension.class).getResults();
    }

    @Override
    public List<MovieExtension.MovieResponse> discoverMovies(String genres) throws JsonProcessingException {
        String json = movieExtensionRepository.discoverMovies(1, "vote_average.desc", 100,
                "en", genres, ACCEPT_HEADER, API_KEY);
        return mapper.readValue(json, MovieExtension.class).getResults();
    }
}