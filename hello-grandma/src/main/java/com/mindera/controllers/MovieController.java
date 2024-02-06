package com.mindera.controllers;

import com.mindera.dtos.MovieGetDto;
import com.mindera.exceptions.movie.InvalidGenreException;
import com.mindera.exceptions.movie.MovieNotFoundException;
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
    public RestResponse<List<MovieGetDto>> getAllMovies() {
        return RestResponse.ok(movieService.getAllMovies());
    }

    @GET
    @Path("/{id}")
    public RestResponse<MovieGetDto> getMovieById(@PathParam("id") Long id) throws MovieNotFoundException {
        return RestResponse.ok(movieService.getMovieById(id));
    }

    @GET
    @Path("/title/{title}")
    public RestResponse<List<MovieGetDto>> getMovieByTitle(@PathParam("title") String title) {
        return RestResponse.ok(movieService.getMoviesByTitle(title));
    }

    @GET
    @Path("/recommendation/{id}")
    public RestResponse<List<MovieGetDto>> getMovieRecommendation(@PathParam("id") Integer id) {
        return RestResponse.ok(movieService.getMovieRecommendation(id));
    }

    @GET
    @Path("/genres/{genre}")
    public RestResponse<List<MovieGetDto>> discoverMovies(@PathParam("genre") String genre)throws InvalidGenreException {
        return RestResponse.ok(movieService.discoverMovies(genre));
    }

    @GET
    @Path("/top")
    public RestResponse<List<MovieGetDto>> getTopRatedMovies() {
        return RestResponse.ok(movieService.getTopFiveMovies());
    }

}