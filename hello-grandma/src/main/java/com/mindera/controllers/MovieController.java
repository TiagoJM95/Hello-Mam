package com.mindera.controllers;

import com.fasterxml.jackson.core.JsonToken;
import com.mindera.dtos.create.MovieCreateDto;
import com.mindera.dtos.get.MovieGetDto;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.external.MovieExtension;
import com.mindera.external.MovieExtensionClient;
import com.mindera.services.MovieService;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonParser;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestResponse;

import static com.mindera.util.Keys.*;
import java.util.List;
import java.util.Set;

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
    public JsonArray getMovieRecommendation(@PathParam("movieId") String movieId){
        JsonObject o = movieExtensionClient.getMovieRecommendation(movieId, ACCEPT_HEADER, API_KEY);
        return o.getJsonArray("results");
    }

    @GET
    @Path("/find/{externalId}")
    public MovieExtension getMovieRecommendationByGenre(@PathParam("externalId") String externalId){

    }
}