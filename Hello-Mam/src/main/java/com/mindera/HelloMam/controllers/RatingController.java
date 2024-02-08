package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.dtos.updates.RatingUpdateRatingDto;
import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.entities.Rating;
import com.mindera.HelloMam.exceptions.media.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.rating.DuplicateRatingException;
import com.mindera.HelloMam.exceptions.rating.RatingNotFoundException;
import com.mindera.HelloMam.exceptions.user.UserNotFoundException;
import com.mindera.HelloMam.services.implementations.RatingServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Rating", description = "The Rating API")
@RestController
@RequestMapping("api/v1/rating")
public class RatingController {

    private final RatingServiceImpl ratingService;

    @Autowired
    public RatingController(RatingServiceImpl ratingService) {
        this.ratingService = ratingService;
    }

    @Operation(
            summary = "Get all ratings",
            description = "Get all rating entries from the database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of rating entries",
            content = {@Content(schema = @Schema(implementation = Rating.class), mediaType = "application/json")})
    @GetMapping("/")
    public ResponseEntity<List<RatingGetDto>> getAllRating() {
        return ResponseEntity.ok(ratingService.getAllRating());
    }

    @Operation(
            summary = "Get a rating entry by id",
            description = "Get a Rating object by specifying its id."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Rating object",
                    content = {@Content(schema = @Schema(implementation = Rating.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The Rating object with the specified id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide an ID to be searched", required = true)
    @GetMapping("/{ratingId}")
    public ResponseEntity<RatingGetDto> getRatingById(@PathVariable("ratingId") Long id) throws RatingNotFoundException {
        return ResponseEntity.ok(ratingService.getRatingById(id));
    }
    @Operation(
            summary = "Get a rating entry by its user id",
            description = "Get a Rating object by specifying its user id."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Rating object",
                    content = {@Content(schema = @Schema(implementation = Rating.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The Rating object with the specified user id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide an ID to be searched", required = true)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingGetDto>> getRatingByUserId(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }
    @Operation(
            summary = "Get a rating entry by media id",
            description = "Get a Rating object by specifying its media id."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Rating object",
                    content = {@Content(schema = @Schema(implementation = Rating.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The Rating object with the specified media id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide an ID to be searched", required = true)
    @GetMapping("/media/{mediaId}")
    public ResponseEntity<List<RatingGetDto>> getRatingByMediaId(@PathVariable Long mediaId) throws MediaNotFoundException {
        return ResponseEntity.ok(ratingService.getRatingByMediaId(mediaId));
    }
    @Operation(
            summary = "Create a new rating entry",
            description = "Create a new Rating object in the database, by providing the necessary information."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Rating Created",
                    content = {@Content(schema = @Schema(implementation = Rating.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "User or Media not found.",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "409",
                    description = "The Rating object already exists.",
                    content = {@Content(schema = @Schema())})
    })
    @PostMapping("/")
    public ResponseEntity<RatingGetDto> addNewRating(@Valid @RequestBody RatingCreateDto ratingCreateDto) throws UserNotFoundException, MediaNotFoundException, DuplicateRatingException {
        return new ResponseEntity<>(ratingService.addNewRating(ratingCreateDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update a rating entry by id",
            description = "Update a Rating object by specifying its id and providing the necessary information."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "Updated Rating object",
                    content = {@Content(schema = @Schema(implementation = Rating.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The Rating object with the specified id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide an ID to be searched", required = true)
    @PatchMapping("/update/{ratingId}")
    public ResponseEntity<RatingGetDto> updateRating(@PathVariable("ratingId") Long ratingId,
                                                     @Valid @RequestBody RatingUpdateRatingDto ratingUpdateRatingDto) throws RatingNotFoundException {
        return new ResponseEntity<>(ratingService.updateRating(ratingId, ratingUpdateRatingDto), HttpStatus.ACCEPTED);
    }
}