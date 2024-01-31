package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.exceptions.media.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.media.RefIdNotFoundException;
import com.mindera.HelloMam.exceptions.MediaTypeNotFoundException;
import com.mindera.HelloMam.externals.External;
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
    private final External external;

    @Autowired
    public MediaController(MediaServiceImpl mediaService, External external) {
        this.mediaService = mediaService;
        this.external = external;
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
        return ResponseEntity.ok(external.getAllMovies());
    }

    @GetMapping("/grandma/id/{refId}")
    public ResponseEntity<String> getMovieById(@PathVariable("refId") String refId){
        return ResponseEntity.ok(external.getMovieById(refId));
    }

    @GetMapping("/grandma/details/{tmbdId}")
    public ResponseEntity<String> getMovieDetails(@PathVariable("tmbdId") Integer tmbdId){
        return ResponseEntity.ok(external.getMovieDetails(tmbdId));
    }

    @GetMapping("/grandma/recommendations/{tmbdId}")
    public ResponseEntity<String> getMovieRecommendations(@PathVariable("tmbdId") Integer tmbdId){
        return ResponseEntity.ok(external.getMovieRecommendations(tmbdId));
    }

    @GetMapping("/grandma/discover/{genreId}")
    public ResponseEntity<String> getDiscoverMovies(@PathVariable("genreId") String genreId){
        return ResponseEntity.ok(external.getDiscoverMovies(genreId));
    }
}