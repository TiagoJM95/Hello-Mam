package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.dtos.updates.RatingUpdateDto;
import com.mindera.HelloMam.exceptions.media_exceptions.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.rating_exceptions.RatingNotFoundException;
import com.mindera.HelloMam.services.implementations.RatingServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rating")
public class RatingController {

    private final RatingServiceImpl ratingService;

    @Autowired
    public RatingController(RatingServiceImpl ratingService) {
        this.ratingService = ratingService;
    }


    @GetMapping("/")
    public ResponseEntity<List<RatingGetDto>> getAllRating() {
        return new ResponseEntity<>(ratingService.getAllRating(), HttpStatus.OK);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<RatingGetDto> getRatingById(@PathVariable("ratingId") Integer id) throws RatingNotFoundException {
        return new ResponseEntity<>(ratingService.getRatingById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingGetDto>> getRatingByUserId(@PathVariable("userId") Long userId) throws Exception {
        return new ResponseEntity<>(ratingService.getRatingByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/media/{mediaId}")
    public ResponseEntity<List<RatingGetDto>> getRatingByMediaId(@PathVariable Integer mediaId) throws MediaNotFoundException {
        return new ResponseEntity<>(ratingService.getRatingByMediaId(mediaId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<RatingGetDto> addNewRating(@Valid @RequestBody RatingCreateDto ratingCreateDto) throws Exception {
        return new ResponseEntity<>(ratingService.addNewRating(ratingCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{ratingId}")
    public ResponseEntity<RatingGetDto> updateRating(@PathVariable("ratingId") Integer ratingId, @Valid @RequestBody RatingUpdateDto ratingUpdateDto) throws RatingNotFoundException {
        return new ResponseEntity<>(ratingService.updateRating(ratingId, ratingUpdateDto), HttpStatus.ACCEPTED);
    }
}
