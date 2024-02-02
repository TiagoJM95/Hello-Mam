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

    @GetMapping("/")
    public ResponseEntity<List<MediaGetDto>> getAllMedia() {
        return ResponseEntity.ok(mediaService.getAllMedia());
    }

    @GetMapping("/{mediaId}")
    public ResponseEntity<MediaGetDto> getMediaById(@PathVariable("mediaId") Long id) throws MediaNotFoundException {
        return ResponseEntity.ok(mediaService.getMediaById(id));
    }

    @GetMapping("/type/{mediaType}")
    public ResponseEntity<List<MediaGetDto>> getMediaByType(@PathVariable("mediaType") String type) throws MediaTypeNotFoundException {
        return ResponseEntity.ok(mediaService.getMediaByType(type));
    }

    @GetMapping("/ref/{mediaRefId}")
    public ResponseEntity<MediaGetDto> getMediaByRefId(@PathVariable("mediaRefId") String refId) throws RefIdNotFoundException {
        return ResponseEntity.ok(mediaService.getMediaByRefId(refId));
    }

    @PostMapping("/")
    public ResponseEntity<MediaGetDto> addNewMedia(@Valid @RequestBody MediaCreateDto mediaCreateDto) throws MediaTypeNotFoundException {
        return new ResponseEntity<>(mediaService.addNewMedia(mediaCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/grandma")
    public ResponseEntity<String> getAllMovies(){
        return ResponseEntity.ok(externalMovies.getAllMovies());
    }

    @GetMapping("/grandma/{type}")
    public ResponseEntity<String> getMoviesByType(@PathVariable("type") String type) {
        switch (type) {
            case "movie":
                return getAllMovies();
            case "videogame":
                return ResponseEntity.ok(externalGames.getAllGames());
            default:
                return ResponseEntity.badRequest().body("Invalid type");
        }
    }

    @GetMapping("/grandma/id/{refId}")
    public ResponseEntity<String> getMovieById(@PathVariable("refId") String refId){
        return ResponseEntity.ok(externalMovies.getMovieById(refId));
    }

    @GetMapping("/grandma/title/{title}")
    public ResponseEntity<String> getMovieDetails(@PathVariable("tmbdId") String title){
        return ResponseEntity.ok(externalMovies.getMovieByTitle(title));
    }

    @GetMapping("/grandma/recommendations/{tmbdId}")
    public ResponseEntity<String> getMovieRecommendations(@PathVariable("tmbdId") Integer tmbdId){
        return ResponseEntity.ok(externalMovies.getMovieRecommendations(tmbdId));
    }

    @GetMapping("/grandma/discover/{genreId}")
    public ResponseEntity<String> getDiscoverMovies(@PathVariable("genreId") String genreId){
        return ResponseEntity.ok(externalMovies.getDiscoverMovies(genreId));
    }
}