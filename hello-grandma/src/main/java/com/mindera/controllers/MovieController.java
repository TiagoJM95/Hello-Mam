package com.mindera.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindera.converters.MovieConverter;
import com.mindera.dtos.create.MovieCreateDto;
import com.mindera.dtos.get.MovieGetDto;
import com.mindera.entities.Movie;
import com.mindera.enums.MovieGenres;
import com.mindera.exceptions.movie.InvalidGenreException;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.external.*;
import com.mindera.services.MovieService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestResponse;

import static com.mindera.util.Keys.*;
import java.util.List;

@Path("/api/v1/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieController {

    @Inject
    MovieService movieService;

    @Inject
    @RestClient
    MovieExtensionClient movieExtensionClient;

    @GET
    public RestResponse<List<MovieGetDto>> getAllMovies() {
        return RestResponse.ok(movieService.getAllMovies());
    }

    @GET
    @Path("/id/{id}")
    public RestResponse<MovieGetDto> getMovieById(@PathParam("id") String id) throws MovieNotFoundException {
        return RestResponse.ok(movieService.findById(id));
    }

    @POST
    public RestResponse<MovieGetDto> create(Movie movie) {
        return RestResponse.accepted(movieService.create(movie));
    }

    @GET
    @Path("/external/{movieId}")
    public RestResponse<MovieDetails> getMovieByIdExternal(@PathParam("movieId") String movieId) throws JsonProcessingException {
        String jsonString = movieExtensionClient.getMovieById(movieId, ACCEPT_HEADER, API_KEY);
        ObjectMapper mapper = new ObjectMapper();
        return RestResponse.ok(mapper.readValue(jsonString, MovieDetails.class));
    }

    @GET
    @Path("/recommendations/{movieId}")
    public RestResponse<List<MovieGetDto>> getMovieRecommendation(@PathParam("movieId") String movieId) throws JsonProcessingException {
        String jsonString = movieExtensionClient.getMovieRecommendation(movieId, ACCEPT_HEADER, API_KEY);
        ObjectMapper mapper = new ObjectMapper();
        return RestResponse.ok(mapper.readValue(jsonString, MovieResponse.class).getResults().stream().map(MovieConverter::fromEntityToGetDto).toList());
    }

    @GET
    @Path("/discover/{genre}")
    public RestResponse<List<MovieGetDto>> discoverMovies(@PathParam("genre") String genre) throws InvalidGenreException, JsonProcessingException {
        MovieGenres movieGenres = MovieGenres.getMovieGenreByName(genre).orElseThrow(() -> new InvalidGenreException("Invalid Genre"));
        String jsonString = movieExtensionClient.discoverMovies(1, "vote_average.desc", 100, "en", String.valueOf(movieGenres.getId()), ACCEPT_HEADER, API_KEY);
        ObjectMapper mapper = new ObjectMapper();
        return RestResponse.ok(mapper.readValue(jsonString, MovieResponse.class).getResults().stream().map(MovieConverter::fromEntityToGetDto).toList());
    }
}