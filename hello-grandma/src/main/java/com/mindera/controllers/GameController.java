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
    GameServiceImpl gameService;

    @GET
    public RestResponse<List<GameGetDto>> getAllGames(){
        return RestResponse.ok(gameService.getAllGames());
    }

    @GET
    @Path("/{id}")
    public RestResponse<GameGetDto> getGameById(@PathParam("id") int id) throws GameNotFoundException {
        return RestResponse.ok(gameService.getGameById(id));
    }

    @GET
    @Path("/title/{title}")
    public RestResponse<List<GameGetDto>> getGamesByTitle(@PathParam("title") String title) {
        return RestResponse.ok(gameService.getGamesByTitle(title));
    }

    @GET
    @Path("/recommendation/{id}")
    public RestResponse<List<GameGetDto>> getGameRecommendations(@PathParam("id") int id) {
        return RestResponse.ok(gameService.getGameRecommendations(id));
    }

    @GET
    @Path("/genres/{genre}")
    public RestResponse<List<GameGetDto>> getGamesByGenre(@PathParam("genre") String genre) throws GameGenreNotFoundException {
        return RestResponse.ok(gameService.getGamesByGenre(genre));
    }

    @GET
    @Path("/top")
    public RestResponse<List<GameGetDto>> getTopRatedGames() {
        return RestResponse.ok(gameService.getTopRatedGames());
    }
}