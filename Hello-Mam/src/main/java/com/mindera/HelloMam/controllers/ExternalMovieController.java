package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.externals.models.ExternalMovie;
import com.mindera.HelloMam.services.implementations.ExternalMovieServiceImpl;
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
@Tag(name = "Movies", description = "The Movies API")
@RestController
@RequestMapping("api/v1/movies")
public class ExternalMovieController {

    private final ExternalMovieServiceImpl externalMovieService;

    @Autowired
    public ExternalMovieController(ExternalMovieServiceImpl externalMovieService) {
        this.externalMovieService = externalMovieService;
    }

    @Operation(
            summary = "Get all movies",
            description = "Get all movies from the Grandma API"
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of movies",
            content = {@Content(schema = @Schema(implementation = ExternalMovie.class), mediaType = "application/json")})
    @GetMapping("/")
    public ResponseEntity<List<ExternalMovie>> getAllMovies() throws RefIdNotFoundException {
        return ResponseEntity.ok(externalMovieService.getAllMovies());
    }

    @Operation(
            summary = "Get a movie by id",
            description = "Get an ExternalMovie object by specifying its id."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ExternalMovie object",
                    content = {@Content(schema = @Schema(implementation = ExternalMovie.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The ExternalMovie object with the specified id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide an ID to be searched", required = true)
    @GetMapping("/id/{id}")
    public ResponseEntity<ExternalMovie> getMovieById(@PathParam("id") Long id){
        return ResponseEntity.ok(externalMovieService.getMovieById(id));
    }

    @Operation(
            summary = "Get movies by title",
            description = "Get a list of ExternalMovie objects by specifying providing a title."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of ExternalMovie objects with the specified title.",
            content = {@Content(schema = @Schema(implementation = ExternalMovie.class), mediaType = "application/json")}
    )
    @Parameter(name = "title",description = "Provide a title to be searched", required = true)
    @GetMapping("/title/{title}")
    public ResponseEntity<List<ExternalMovie>> getGameByTitle(@PathParam("title") String title) throws RefIdNotFoundException {
        return ResponseEntity.ok(externalMovieService.getMovieByTitle(title));
    }

    @Operation(
            summary = "Get movie recommendations",
            description = "Get a list of ExternalMovie objects by providing the id of another movie."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of recommended ExternalMovie objects.",
                    content = {@Content(schema = @Schema(implementation = ExternalMovie.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The ExternalMovie object with the specified id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide an ExternalMovie ID to be searched", required = true)
    @GetMapping("/recommendations/{id}")
    public ResponseEntity<List<ExternalMovie>> getGameRecommendations(@PathParam("id") int id) throws RefIdNotFoundException {
        return ResponseEntity.ok(externalMovieService.getMovieRecommendations(id));
    }

    @Operation(
            summary = "Get movies by genre",
            description = "Get a list of ExternalMovie objects by providing a movie genre."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of ExternalMovie objects with the specified genre.",
                    content = {@Content(schema = @Schema(implementation = ExternalMovie.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "400",
                    description = "The provided genre is not valid.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "genre",description = "Provide a genre to be searched", required = true)
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<ExternalMovie>> getGameByGenre(@PathParam("genre") String genre) throws RefIdNotFoundException {
        return ResponseEntity.ok(externalMovieService.getDiscoverMovies(genre));
    }

    @Operation(
            summary = "Get top rated movies",
            description = "Get a list of ExternalMovie objects sorted by rating."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of ExternalMovie objects.",
                    content = {@Content(schema = @Schema(implementation = ExternalMovie.class), mediaType = "application/json")})
    })
    @GetMapping("/top")
    public ResponseEntity<List<ExternalMovie>> getTopFiveVideoGames() throws RefIdNotFoundException {
        return ResponseEntity.ok(externalMovieService.getTopRatedMovies());
    }
}