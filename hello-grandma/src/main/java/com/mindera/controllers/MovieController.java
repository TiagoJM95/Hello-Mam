package com.mindera.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindera.dtos.create.MovieCreateDto;
import com.mindera.dtos.get.MovieGetDto;
import com.mindera.enums.MovieGenre;
import com.mindera.exceptions.movie.InvalidGenreException;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.external.*;
import com.mindera.services.MovieService;
import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
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
    @Path("/{id}")
    public RestResponse<MovieGetDto> getMovieById(@PathParam("id") String id) throws MovieNotFoundException {
        return RestResponse.ok(movieService.findById(id));
    }

    @GET
    @Path("/director/{director}")
    public RestResponse<List<MovieGetDto>> getMovieByDirector(@PathParam("director") String director) {
        return RestResponse.ok(movieService.findByDirector(director));
    }

    @POST
    public RestResponse<MovieGetDto> create(MovieCreateDto movie) {
        return RestResponse.accepted(movieService.create(movie));
    }

    @GET
    @Path("/recommendations/{movieId}")
    public List<MovieExtensionGetDto> getMovieRecommendation(@PathParam("movieId") String movieId) throws JsonProcessingException {
        String jsonString = movieExtensionClient.getMovieRecommendation(movieId, ACCEPT_HEADER, API_KEY);
        ObjectMapper mapper = new ObjectMapper();
        return MovieExtensionConverter.convertList(mapper.readValue(jsonString, MovieResponse.class).getResults());
    }

    @GET
    @Path("/discover/{genre}")
    public List<MovieExtensionGetDto> discoverMovies(@PathParam("genre") String genre) throws InvalidGenreException, JsonProcessingException {
        MovieGenre movieGenre = MovieGenre.getMovieGenreByName(genre).orElseThrow(() -> new InvalidGenreException("Invalid Genre"));
        String jsonString = movieExtensionClient.discoverMovies(1, "vote_average.desc", 100, "en", String.valueOf(movieGenre.getId()), ACCEPT_HEADER, API_KEY);
        ObjectMapper mapper = new ObjectMapper();
        return MovieExtensionConverter.convertList(mapper.readValue(jsonString, MovieResponse.class).getResults());
    }
}