package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.exceptions.media.MediaNotFoundException;
import com.mindera.HelloMam.services.implementations.MediaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/media")
public class MediaController {

    private final MediaServiceImpl mediaService;

    @Autowired
    public MediaController(MediaServiceImpl mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MediaGetDto>> getAllMedia() {
        return ResponseEntity.ok(mediaService.getAllMedia());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MediaGetDto> getMediaById(@PathVariable Long id) throws MediaNotFoundException {
        return ResponseEntity.ok(mediaService.getMediaById(id));
    }
}