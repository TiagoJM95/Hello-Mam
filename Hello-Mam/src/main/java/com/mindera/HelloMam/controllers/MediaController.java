package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.MediaCreateDto;
import com.mindera.HelloMam.dtos.gets.MediaGetDto;
import com.mindera.HelloMam.dtos.gets.UserGetDto;
import com.mindera.HelloMam.enums.MediaType;
import com.mindera.HelloMam.exceptions.media_exceptions.MediaNotFoundException;
import com.mindera.HelloMam.services.implementations.MediaServiceImpl;
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

    private final MediaServiceImpl mediaServiceImpl;

    @Autowired
    public MediaController(MediaServiceImpl mediaServiceImpl) {
        this.mediaServiceImpl = mediaServiceImpl;
    }

    @GetMapping("/")
    public ResponseEntity<List<MediaGetDto>> getAllMedia() {
        return ResponseEntity.ok(mediaServiceImpl.getAllMedia());
    }

//    @GetMapping("/")
//    public ResponseEntity<List<UserGetDto>> getUser() {
//        return ResponseEntity.ok(userServiceImpl.findAll());
//    }

    @GetMapping(path = "{mediaId}")
    public ResponseEntity<MediaGetDto> getMediaById(@PathVariable("mediaId") Integer id) throws MediaNotFoundException {
        return new ResponseEntity<>(mediaServiceImpl.getMediaById(id), HttpStatus.OK);
    }

    @GetMapping(path = "{mediaType}")
    public ResponseEntity<List<MediaGetDto>> getMediaByType(@PathVariable("mediaType") MediaType type) {
        return new ResponseEntity<>(mediaServiceImpl.getMediaByType(type), HttpStatus.OK);
    }


    @GetMapping(path = "{mediaRefId}")
    public ResponseEntity<MediaGetDto> getMediaByRefId(@PathVariable("mediaRefId") String refId) {
        return new ResponseEntity<>(mediaServiceImpl.getMediaByRefId(refId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<MediaGetDto> addNewMedia(@Valid @RequestBody MediaCreateDto mediaCreateDto) {
        return new ResponseEntity<>(mediaServiceImpl.addNewMedia(mediaCreateDto), HttpStatus.CREATED);
    }
}
