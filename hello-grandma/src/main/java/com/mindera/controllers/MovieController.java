package com.mindera.controllers;

import com.mindera.dtos.create.MovieCreateDto;
import com.mindera.dtos.get.MovieGetDto;
import com.mindera.exceptions.movie.MovieNotFoundException;
import com.mindera.services.MovieService;
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
    MovieService movieService;

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
}