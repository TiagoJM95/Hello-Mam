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
            description = "Get all games from the Grandma API"
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
            description = "Get an ExternalGame object by specifying its id."
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
    @Parameter(name = "id",description = "Provide an ID to be searched", required = true)
    @GetMapping("/id/{id}")
    public ResponseEntity<ExternalGame> getVideogameById(@PathParam("id") int id){
        return ResponseEntity.ok(externalGameService.getVideogameById(id));
    }

    @Operation(
            summary = "Get games by title",
            description = "Get a list of ExternalGame objects by specifying providing a title."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of ExternalGame objects with the specified title.",
            content = {@Content(schema = @Schema(implementation = ExternalGame.class), mediaType = "application/json")}
    )
    @Parameter(name = "title",description = "Provide a title to be searched", required = true)
    @GetMapping("/title/{title}")
    public ResponseEntity<List<ExternalGame>> getGameByTitle(@PathParam("title") String title) {
        return ResponseEntity.ok(externalGameService.getGameByTitle(title));
    }

    @Operation(
            summary = "Get game recommendations",
            description = "Get a list of ExternalGame objects by providing the id of another game."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of recommended ExternalGame objects.",
                    content = {@Content(schema = @Schema(implementation = ExternalGame.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The ExternalGame object with the specified id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide an ExternalGame ID to be searched", required = true)
    @GetMapping("/recommendations/{id}")
    public ResponseEntity<List<ExternalGame>> getGameRecommendations(@PathParam("id") int id) {
        return ResponseEntity.ok(externalGameService.getGameRecommendations(id));
    }

    @Operation(
            summary = "Get games by genre",
            description = "Get a list of ExternalGame objects by providing a game genre."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of ExternalGame objects with the specified genre.",
                    content = {@Content(schema = @Schema(implementation = ExternalGame.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "400",
                    description = "The provided genre is not valid.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "genre",description = "Provide a genre to be searched", required = true)
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<ExternalGame>> getGameByGenre(@PathParam("genre") String genre) {
        return ResponseEntity.ok(externalGameService.getGameByGenre(genre));
    }

    @Operation(
            summary = "Get top rated games",
            description = "Get a list of ExternalGame objects sorted by rating."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of ExternalGame objects.",
                    content = {@Content(schema = @Schema(implementation = ExternalGame.class), mediaType = "application/json")})
    })
    @GetMapping("/top")
    public ResponseEntity<List<ExternalGame>> getTopFiveVideoGames() {
        return ResponseEntity.ok(externalGameService.getTopFiveVideoGames());
    }
}