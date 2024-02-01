package com.mindera.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindera.entities.MovieExtension;
import com.mindera.repositories.MovieExtensionRepository;
import com.mindera.services.interfaces.MovieExtensionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

import static com.mindera.util.Keys.ACCEPT_HEADER;
import static com.mindera.util.Keys.API_KEY;

@ApplicationScoped
public class MovieExtensionServiceImpl implements MovieExtensionService {

    @Inject
    @RestClient
    MovieExtensionRepository movieExtensionRepository;

    @Override
    public List<MovieExtension.MovieResponse> getMovieRecommendation(String movieId) throws JsonProcessingException {
        String json = movieExtensionRepository.getMovieRecommendation(movieId, ACCEPT_HEADER, API_KEY);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, MovieExtension.class).getResults();
    }
}