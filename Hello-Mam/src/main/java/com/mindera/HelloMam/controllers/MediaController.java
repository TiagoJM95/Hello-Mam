package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.exceptions.media.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.externals.models.ExternalGame;
import com.mindera.HelloMam.externals.clients.ExternalGameClient;
import com.mindera.HelloMam.services.implementations.MediaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/media")
public class MediaController {

    private final MediaServiceImpl mediaService;
    private final ExternalGameClient externalGameClient;

    @Autowired
    public MediaController(MediaServiceImpl mediaService, ExternalGameClient externalGameClient) {
        this.mediaService = mediaService;
        this.externalGameClient = externalGameClient;
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

    //Get All Videogames from External API
    @GetMapping("/videogames/all/")
    public ResponseEntity<List<ExternalGame>> getAllVideogames() {
        return ResponseEntity.ok(externalGameClient.getAllVideogames());
    }

    //Get Videogame By Id from External API
    @GetMapping("/videogames/{refId}")
    public ResponseEntity<ExternalGame> getVideogameById(@PathVariable("refId") String refId) {
        return ResponseEntity.ok(externalGameClient.getGameById(refId));
    }

    //Get Videogame By Title from External API
    @GetMapping("/videogames/title/{title}")
    public ResponseEntity<List<ExternalGame>> getVideogameByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok(externalGameClient.getGameByTitle(title));
    }

    @GetMapping("/videogames/recommendations/{refId}")
    public ResponseEntity<List<ExternalGame>> getGameRecommendations(@PathVariable("refId") String refId) {
        return ResponseEntity.ok(externalGameClient.getGameRecommendations(Integer.valueOf(refId)));
    }




    /*@GetMapping("/recommendations/all/")
    public ResponseEntity<List<ExternalMovie>> getAllMovies() {
        return ResponseEntity.ok(mediaService.getAllMovies());
    }*/

    /*@GetMapping("/recommendations/{refId}")
    public ResponseEntity<ExternalMovie> getMovieById(@PathVariable("refId") String refId) {
        return ResponseEntity.ok(mediaService.getMovieById(refId));
    }*/


    }