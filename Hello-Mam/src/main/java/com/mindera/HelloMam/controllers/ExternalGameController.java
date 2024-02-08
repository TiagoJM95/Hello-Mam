package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.externals.models.ExternalGame;
import com.mindera.HelloMam.services.implementations.ExternalGameServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Games", description = "The Games API")
@RestController
@RequestMapping("api/v1/games")
public class ExternalGameController {

    private final ExternalGameServiceImpl externalGameService;

    @Autowired
    public ExternalGameController(ExternalGameServiceImpl externalGameService) {
        this.externalGameService = externalGameService;
    }

    @Operation(
            summary = "Get all games",
            description = "Get all games from the Grandma API",
            tags = {"Games", "get"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of games",
            content = {@Content(schema = @Schema(implementation = ExternalGame.class), mediaType = "application/json")})
    @GetMapping("/")
    public ResponseEntity<List<ExternalGame>> getAllVideogames(){
        return ResponseEntity.ok(externalGameService.getAllVideogames());
    }

    @Operation(
            summary = "Get a game by id",
            description = "Get a ExternalGame object by specifying its id. The response is ExternalGame object with id, name, release date, rating and a list of it's genres.",
            tags = {"Games", "get", "id"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ExternalGame object",
                    content = {@Content(schema = @Schema(implementation = ExternalGame.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The ExternalGame object with the specified id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide a ExternalGame ID to be searched", required = true)
    @GetMapping("/id/{id}")
    public ResponseEntity<ExternalGame> getVideogameById(@PathParam("id") int id){
        return ResponseEntity.ok(externalGameService.getVideogameById(id));
    }


    @GetMapping("/title/{title}")
    public ResponseEntity<List<ExternalGame>> getGameByTitle(@PathParam("title") String title) {
        return ResponseEntity.ok(externalGameService.getGameByTitle(title));
    }

    @GetMapping("/recommendations/{id}")
    public ResponseEntity<List<ExternalGame>> getGameRecommendations(@PathParam("id") int id) {
        return ResponseEntity.ok(externalGameService.getGameRecommendations(id));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<ExternalGame>> getGameByGenre(@PathParam("genre") String genre) {
        return ResponseEntity.ok(externalGameService.getGameByGenre(genre));
    }

    @GetMapping("/top")
    public ResponseEntity<List<ExternalGame>> getTopFiveVideoGames() {
        return ResponseEntity.ok(externalGameService.getTopFiveVideoGames());
    }

}