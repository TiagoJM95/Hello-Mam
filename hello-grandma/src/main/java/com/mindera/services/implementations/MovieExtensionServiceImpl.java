package com.mindera.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindera.entities.Movie;
import com.mindera.entities.MovieExtension;
import com.mindera.repositories.MovieExtensionRepository;
import com.mindera.services.interfaces.MovieExtensionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

import static com.mindera.util.Keys.ACCEPT_HEADER;
import static com.mindera.util.Keys.API_KEY;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
public class MovieExtensionServiceImpl implements MovieExtensionService {

    @Inject
    @RestClient
    MovieExtensionRepository movieExtensionRepository;

    @Inject
    ObjectMapper mapper;

    @Override
    public Movie getMovieDetailsByTmdbId(String tmdbId) throws JsonProcessingException {
        /*String json = movieExtensionRepository.getMovieById(tmdbId, MediaType.APPLICATION_JSON, API_KEY);
        return mapper.readValue(json, Movie.class);*/
        return movieExtensionRepository.getMovieById(tmdbId, APPLICATION_JSON, API_KEY);
    }

    @Override
    public List<MovieExtension.MovieResponse> getMovieRecommendation(Integer movieId) throws JsonProcessingException {
        /*String json = movieExtensionRepository.getMovieRecommendation(movieId, ACCEPT_HEADER, API_KEY);
        return mapper.readValue(json, MovieExtension.class).getResults();*/
        return movieExtensionRepository.getMovieRecommendation(movieId, ACCEPT_HEADER, API_KEY).getResults();
    }

    @Override
    public List<MovieExtension.MovieResponse> discoverMovies(String genres) throws JsonProcessingException {
        /*String json = movieExtensionRepository.discoverMovies(1, "vote_average.desc", 100,
                "en", genres, ACCEPT_HEADER, API_KEY);
        return mapper.readValue(json, MovieExtension.class).getResults();*/
        return movieExtensionRepository.discoverMovies(1, "vote_average.desc", 100,
                "en", genres, APPLICATION_JSON, API_KEY).getResults();
    }

    @Override
    public List<MovieExtension.MovieResponse> findMovieByTitle(String title) throws JsonProcessingException {
        /*String json = movieExtensionRepository.findMovieByTitle(title, "en-US", 1, ACCEPT_HEADER, API_KEY);
        return mapper.readValue(json, MovieExtension.class).getResults();*/
        return movieExtensionRepository.findMovieByTitle(title, "en-US", 1, APPLICATION_JSON, API_KEY).getResults();
    }
}