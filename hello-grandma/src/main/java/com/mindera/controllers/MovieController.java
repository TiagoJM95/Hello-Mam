package com.mindera.controllers;

import com.mindera.dtos.MovieGetDto;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.external.MovieExtensionClient;
import com.mindera.services.implementations.MovieServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/api/v1/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieController {

    @Inject
    MovieServiceImpl movieService;

    @Inject
    @RestClient
    MovieExtensionClient movieExtensionClient;

    @GET
    @Path("/{id}")
    public RestResponse<MovieGetDto> getMovieById(@PathParam("id") String id) throws MovieNotFoundException {
        return RestResponse.ok(movieService.getMovieById(id));
    }

    @GET
    @Path("/title/{title}")
    public RestResponse<List<MovieGetDto>> getMovieByTitle(@PathParam("title") String title) throws MovieNotFoundException {
        return RestResponse.ok(movieService.getMoviesByTitle(title));
    }

    @GET
    public RestResponse<List<MovieGetDto>> getAllMovies() {
        return RestResponse.ok(movieService.getAllMovies());
    }
}