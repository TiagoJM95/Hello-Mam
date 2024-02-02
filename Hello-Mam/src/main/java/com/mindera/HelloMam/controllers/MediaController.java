package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.exceptions.media.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.externals.ExternalGames;
import com.mindera.HelloMam.externals.ExternalMovies;
import com.mindera.HelloMam.services.implementations.MediaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/media")
public class MediaController {

    private final MediaServiceImpl mediaService;
    private final ExternalMovies externalMovies;
    private final ExternalGames externalGames;

    @Autowired
    public MediaController(MediaServiceImpl mediaService, ExternalMovies externalMovies, ExternalGames externalGames) {
        this.mediaService = mediaService;
        this.externalMovies = externalMovies;
        this.externalGames = externalGames;
    }

    //Get Media from MySql
    @GetMapping("/")
    public ResponseEntity<List<MediaGetDto>> getAllMedia() {
        return ResponseEntity.ok(mediaService.getAllMedia());
    }

    //Get Media By Id from MySql
    @GetMapping("/{mediaId}")
    public ResponseEntity<MediaGetDto> getMediaById(@PathVariable("mediaId") Long id) throws MediaNotFoundException {
        return ResponseEntity.ok(mediaService.getMediaById(id));
    }

    //Get Media By Type from MySql
    @GetMapping("/type/{mediaType}")
    public ResponseEntity<List<MediaGetDto>> getMediaByType(@PathVariable("mediaType") String type) throws MediaTypeNotFoundException {
        return ResponseEntity.ok(mediaService.getMediaByType(type));
    }

    //Get Media By RefId from MySql
    @GetMapping("/ref/{mediaRefId}")
    public ResponseEntity<MediaGetDto> getMediaByRefId(@PathVariable("mediaRefId") String refId) throws RefIdNotFoundException {
        return ResponseEntity.ok(mediaService.getMediaByRefId(refId));
    }

    //Add Media to MySql
    @PostMapping("/")
    public ResponseEntity<MediaGetDto> addNewMedia(@Valid @RequestBody MediaCreateDto mediaCreateDto) throws MediaTypeNotFoundException {
        return new ResponseEntity<>(mediaService.addNewMedia(mediaCreateDto), HttpStatus.CREATED);
    }

    //Get Movies By Type from External API
    @GetMapping("/{type}")
    public ResponseEntity<String> getAllExternalMediaByType(@PathVariable("type") String type) {
        return switch (type) {
            case "movie" -> ResponseEntity.ok(externalMovies.getAllMovies());
            case "videogame" -> ResponseEntity.ok(externalGames.getAllVideogames());
            default -> ResponseEntity.badRequest().body("Invalid type");
        };
    }

    @GetMapping("/{type}/{title}")
    public ResponseEntity<String> getExternalMediaByTitle(@PathVariable("type") String type, @PathVariable("title") String title){
        return switch (type) {
            case "movie" -> ResponseEntity.ok(externalMovies.getMovieByTitle(title));
            case "videogame" -> ResponseEntity.ok(externalGames.getGameByTitle(title));
            default -> ResponseEntity.badRequest().body("Invalid type");
        };
    }


    @GetMapping("/{type}/{refId}")
    public ResponseEntity<String> getExternalMediaByMongoId(@PathVariable("type") String type, @PathVariable("refId") String refId){
        return switch (type) {
            case "movie" -> ResponseEntity.ok(externalMovies.getMovieById(refId));
            case "videogame" -> ResponseEntity.ok(externalGames.getGameById(refId));
            default -> ResponseEntity.badRequest().body("Invalid type");
        };
    }


    @GetMapping("/{type}/recommendations/{id}")
    public ResponseEntity<String> getExternalMediaRecommendationById(@PathVariable("type") String type, @PathVariable("id") Integer tmbdId){
        return switch (type) {
            case "movie" -> ResponseEntity.ok(externalMovies.getMovieRecommendations(tmbdId));
            case "videogame" -> ResponseEntity.ok(externalGames.getGameRecommendations(tmbdId));
            default -> ResponseEntity.badRequest().body("Invalid type");
        };
    }

    @GetMapping("/{type}/discover/{genreId}")
    public ResponseEntity<String> discoverExternalMediaByFilter(@PathVariable("type") String type, @PathVariable("genreId") String genreId){
        return switch (type) {
            case "movie" -> ResponseEntity.ok(externalMovies.getDiscoverMovies(genreId));
            case "videogame" -> ResponseEntity.ok(externalGames.getDiscoverGame(Integer.parseInt(genreId)));
            default -> ResponseEntity.badRequest().body("Invalid type");
        };
    }
}