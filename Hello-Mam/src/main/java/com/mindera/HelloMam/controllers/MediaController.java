package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.exceptions.media.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.services.implementations.MediaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Media", description = "The Media API")
@RestController
@RequestMapping("api/v1/media")
public class MediaController {

    private final MediaServiceImpl mediaService;

    @Autowired
    public MediaController(MediaServiceImpl mediaService) {
        this.mediaService = mediaService;
    }

    @Operation(
            summary = "Get all media",
            description = "Get all media entries from the database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "List of media entries",
            content = {@Content(schema = @Schema(implementation = Media.class), mediaType = "application/json")})
    @GetMapping("/")
    public ResponseEntity<List<MediaGetDto>> getAllMedia() {
        return ResponseEntity.ok(mediaService.getAllMedia());
    }

    @Operation(
            summary = "Get a media entry by id",
            description = "Get a Media object by specifying its id."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Media object",
                    content = {@Content(schema = @Schema(implementation = Media.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The Media object with the specified id was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "id",description = "Provide an ID to be searched", required = true)
    @GetMapping("/id/{id}")
    public ResponseEntity<MediaGetDto> getMediaById(@PathVariable Long id) throws MediaNotFoundException {
        return ResponseEntity.ok(mediaService.getMediaById(id));
    }

    @Operation(
            summary = "Get media entries by its type",
            description = "Get a list of Media objects by specifying its type."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Media object",
                    content = {@Content(schema = @Schema(implementation = Media.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The Media type specified was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "type",description = "Provide a type to be searched", required = true)
    @GetMapping("/type/{type}")
    public ResponseEntity<List<MediaGetDto>> getMediaByType(@RequestParam String type) throws MediaTypeNotFoundException {
        return ResponseEntity.ok(mediaService.getMediaByType(type));
    }

    @Operation(
            summary = "Get a media entry by its refId",
            description = "Get a Media object by specifying its refId."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Media object",
                    content = {@Content(schema = @Schema(implementation = Media.class), mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "The Media refId specified was not found.",
                    content = {@Content(schema = @Schema())})
    })
    @Parameter(name = "refId",description = "Provide a refId to be searched", required = true)
    @GetMapping("/refId/{refId}")
    public ResponseEntity<MediaGetDto> getMediaByRefId(@RequestParam Long refId) throws RefIdNotFoundException {
        return ResponseEntity.ok(mediaService.getMediaByRefId(refId));
    }
}