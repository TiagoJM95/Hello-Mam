package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dto.MediaCreateDto;
import com.mindera.HelloMam.dto.MediaGetDto;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.services.interfaces.MediaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/media")
public class MediaController {

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MediaGetDto>> getAllMedia() {
        return new ResponseEntity<>(mediaService.getAllMedia(), HttpStatus.OK);
    }

    @GetMapping(path = "{mediaId}")
    public ResponseEntity<MediaGetDto> getMediaById(@PathVariable("mediaId") Integer id) {
        return new ResponseEntity<>(mediaService.getMediaById(id), HttpStatus.OK);
    }

    @GetMapping(path = "{mediaType}")
    public ResponseEntity<List<MediaGetDto>> getMediaByType(@PathVariable("mediaType") MediaType type) {
        return new ResponseEntity<>(mediaService.getMediaByType(type), HttpStatus.OK);
    }


    @GetMapping(path = "{mediaRefId}")
    public ResponseEntity<MediaGetDto> getMediaByRefId(@PathVariable("mediaRefId") String refId) {
        return new ResponseEntity<>(mediaService.getMediaByRefId(refId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<MediaGetDto> addNewMedia(@Valid @RequestBody MediaCreateDto mediaCreateDto) {
        return new ResponseEntity<>(mediaService.addNewMedia(mediaCreateDto), HttpStatus.CREATED);
    }
}
