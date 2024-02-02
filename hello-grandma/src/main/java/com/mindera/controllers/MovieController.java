package com.mindera.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindera.dtos.MovieGetDto;
import com.mindera.exceptions.movie.InvalidGenreException;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.entities.MovieExtension;
import com.mindera.services.implementations.MovieExtensionServiceImpl;
import com.mindera.services.implementations.MovieServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/api/v1/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieController {

    @Inject
    MovieServiceImpl movieService;

    @GET
    @Path("/{id}")
    public RestResponse<MovieGetDto> getMovieById(@PathParam("id") String id) throws MovieNotFoundException {
        return RestResponse.ok(movieService.getMovieById(id));
    }

    @GET
    @Path("/title/{title}")
    public RestResponse<List<MovieGetDto>> getMovieByTitle(@PathParam("title") String title) throws JsonProcessingException {
        return RestResponse.ok(movieService.getMoviesByTitle(title));
    }

    @GET
    public RestResponse<List<MovieGetDto>> getAllMovies() {
        return RestResponse.ok(movieService.getAllMovies());
    }

    @GET
    @Path("/recommendation/{id}")
    public RestResponse<List<MovieGetDto>> getMovieRecommendation(@PathParam("id") Integer id) throws JsonProcessingException {
        return RestResponse.ok(movieService.getMovieRecommendation(id));
    }

    @GET
    @Path("/discover/{genres}")
    public RestResponse<List<MovieGetDto>> discoverMovies(@PathParam("genres") String genres) throws JsonProcessingException, InvalidGenreException {
        return RestResponse.ok(movieService.discoverMovies(genres));
    }
}