package com.mindera.controllers;

import com.mindera.dtos.VideogameGetDto;
import com.mindera.exceptions.videogame.GameGenreNotFoundException;
import com.mindera.exceptions.videogame.VideogameNotFoundException;
import com.mindera.services.implementations.VideogameServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/api/v1/videogames")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VideogameController{

    @Inject
    VideogameServiceImpl videogameService;

    @GET
    public RestResponse<List<VideogameGetDto>> getAllVideogames(){
        return RestResponse.ok(videogameService.getAllGames());
    }

    @GET
    @Path("/{id}")
    public RestResponse<VideogameGetDto> getVideogameById(@PathParam("id") int id) throws VideogameNotFoundException {
        return RestResponse.ok(videogameService.getGameById(id));
    }

    @GET
    @Path("/title/{title}")
    public RestResponse<List<VideogameGetDto>> getVideoGameByTitle(@PathParam("title") String title) {
        return RestResponse.ok(videogameService.getGamesByTitle(title));
    }

    @GET
    @Path("/recommendation/{id}")
    public RestResponse<List<VideogameGetDto>> getGameRecommendations(@PathParam("id") int id) {
        return RestResponse.ok(videogameService.getGameRecommendation(id));
    }

    @GET
    @Path("/genres/{genre}")
    public RestResponse<List<VideogameGetDto>> getVideogameByGenre(@PathParam("genre") String genre) throws GameGenreNotFoundException {
        return RestResponse.ok(videogameService.discoverGames(genre));
    }

    @GET
    @Path("/top")
    public RestResponse<List<VideogameGetDto>> getTopFiveVideoGames() {
        return RestResponse.ok(videogameService.getTopFiveVideoGames());
    }
}
