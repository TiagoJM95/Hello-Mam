package com.mindera.controllers;

import com.mindera.dtos.GameGetDto;
import com.mindera.exceptions.game.GameGenreNotFoundException;
import com.mindera.exceptions.game.GameNotFoundException;
import com.mindera.services.implementations.GameServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/api/v1/games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameController {

    @Inject
    GameServiceImpl videogameService;

    @GET
    public RestResponse<List<GameGetDto>> getAllVideogames(){
        return RestResponse.ok(videogameService.getAllGames());
    }

    @GET
    @Path("/{id}")
    public RestResponse<GameGetDto> getVideogameById(@PathParam("id") int id) throws GameNotFoundException {
        return RestResponse.ok(videogameService.getGameById(id));
    }

    @GET
    @Path("/title/{title}")
    public RestResponse<List<GameGetDto>> getVideoGameByTitle(@PathParam("title") String title) {
        return RestResponse.ok(videogameService.getGamesByTitle(title));
    }

    @GET
    @Path("/recommendation/{id}")
    public RestResponse<List<GameGetDto>> getGameRecommendations(@PathParam("id") int id) {
        return RestResponse.ok(videogameService.getGameRecommendation(id));
    }

    @GET
    @Path("/genres/{genre}")
    public RestResponse<List<GameGetDto>> getVideogameByGenre(@PathParam("genre") String genre) throws GameGenreNotFoundException {
        return RestResponse.ok(videogameService.discoverGames(genre));
    }

    @GET
    @Path("/top")
    public RestResponse<List<GameGetDto>> getTopFiveVideoGames() {
        return RestResponse.ok(videogameService.getTopFiveVideoGames());
    }
}