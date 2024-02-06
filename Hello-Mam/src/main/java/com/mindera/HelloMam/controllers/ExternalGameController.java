package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.externals.models.ExternalGame;
import com.mindera.HelloMam.services.implementations.ExternalGameServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/games")
public class ExternalGameController {

    private final ExternalGameServiceImpl externalGameService;

    @Autowired
    public ExternalGameController(ExternalGameServiceImpl externalGameService) {
        this.externalGameService = externalGameService;
    }

    @Operation(summary = "Get all videogames available in the database.")
    @ApiResponse(responseCode = "200", description = "List of videogames available in the database.",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExternalGame.class))})
    @GetMapping("/")
    public ResponseEntity<List<ExternalGame>> getAllVideogames() throws RefIdNotFoundException {
        return ResponseEntity.ok(externalGameService.getAllVideogames());
    }

    @Operation(summary = "Get a videogame by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Videogame found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExternalGame.class))}),
            @ApiResponse(responseCode = "404", description = "Videogame not found.",
                    content = @Content)})
    @GetMapping("/id/{id}")
    public ResponseEntity<ExternalGame> getVideogameById(@PathParam("id") int id){
        return ResponseEntity.ok(externalGameService.getVideogameById(id));
    }

    @Operation(summary = "Get videogames by title.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Videogames found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExternalGame.class))}),
            @ApiResponse(responseCode = "404", description = "Videogames not found.",
                    content = @Content)})
    @GetMapping("/title/{title}")
    public ResponseEntity<List<ExternalGame>> getGameByTitle(@PathParam("title") String title) throws RefIdNotFoundException {
        return ResponseEntity.ok(externalGameService.getGameByTitle(title));
    }

    @Operation(summary = "Get videogames recommendations based on a provided game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Videogames found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExternalGame.class))}),
            @ApiResponse(responseCode = "404", description = "Videogames not found.",
                    content = @Content)})
    @GetMapping("/recommendations/{id}")
    public ResponseEntity<List<ExternalGame>> getGameRecommendations(@PathParam("id") int id) throws RefIdNotFoundException {
        return ResponseEntity.ok(externalGameService.getGameRecommendations(id));
    }

    @Operation(summary = "Get videogames by genre.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Videogames found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExternalGame.class))}),
            @ApiResponse(responseCode = "404", description = "Videogames not found.",
                    content = @Content)})
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<ExternalGame>> getGameByGenre(@PathParam("genre") String genre) throws RefIdNotFoundException {
        return ResponseEntity.ok(externalGameService.getGameByGenre(genre));
    }

    @Operation(summary = "Get the top 5 videogames.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Top 5 videogames found.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExternalGame.class))}),
            @ApiResponse(responseCode = "404", description = "Top 5 videogames not found.",
                    content = @Content)})
    @GetMapping("/top")
    public ResponseEntity<List<ExternalGame>> getTopFiveVideoGames() throws RefIdNotFoundException {
        return ResponseEntity.ok(externalGameService.getTopFiveVideoGames());
    }

}