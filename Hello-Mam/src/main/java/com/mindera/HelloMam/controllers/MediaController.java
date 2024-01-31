package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.exceptions.media_exceptions.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.media_exceptions.RefIdNotFoundException;
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

    @GetMapping("/external")
    public ResponseEntity<String> testExternal() {
        return ResponseEntity.ok(external.testResponse());
    }
}