package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.exceptions.media_exceptions.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.media_exceptions.RefIdNotFoundException;
import com.mindera.HelloMam.exceptions.media_exceptions.TypeNotFoundException;
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

    private final MediaServiceImpl mediaServiceImpl;

    @Autowired
    public MediaController(MediaServiceImpl mediaServiceImpl) {
        this.mediaServiceImpl = mediaServiceImpl;
    }

    @GetMapping("/")
    public ResponseEntity<List<MediaGetDto>> getAllMedia() {
        return ResponseEntity.ok(mediaServiceImpl.getAllMedia());
    }

    @GetMapping("/{mediaId}")
    public ResponseEntity<MediaGetDto> getMediaById(@PathVariable("mediaId") Long id) throws MediaNotFoundException {
        return ResponseEntity.ok(mediaServiceImpl.getMediaById(id));
    }

    @GetMapping("/type/{mediaType}")
    public ResponseEntity<List<MediaGetDto>> getMediaByType(@PathVariable("mediaType") MediaType type) throws TypeNotFoundException {
        return ResponseEntity.ok(mediaServiceImpl.getMediaByType(type));
    }

    @GetMapping("/ref/{mediaRefId}")
    public ResponseEntity<MediaGetDto> getMediaByRefId(@PathVariable("mediaRefId") String refId) throws RefIdNotFoundException {
        return ResponseEntity.ok(mediaServiceImpl.getMediaByRefId(refId));
    }

    @PostMapping("/")
    public ResponseEntity<MediaGetDto> addNewMedia(@Valid @RequestBody MediaCreateDto mediaCreateDto) {
        return new ResponseEntity<>(mediaServiceImpl.addNewMedia(mediaCreateDto), HttpStatus.CREATED);
    }
}
