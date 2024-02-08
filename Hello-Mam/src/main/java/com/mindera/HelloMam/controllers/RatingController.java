package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.dtos.updates.RatingUpdateRatingDto;
import com.mindera.HelloMam.exceptions.media.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.rating.DuplicateRatingException;
import com.mindera.HelloMam.exceptions.rating.RatingNotFoundException;
import com.mindera.HelloMam.exceptions.user.UserNotFoundException;
import com.mindera.HelloMam.services.implementations.RatingServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/rating")
public class RatingController {

    private final RatingServiceImpl ratingService;

    @Autowired
    public RatingController(RatingServiceImpl ratingService) {
        this.ratingService = ratingService;
    }


    @GetMapping("/")
    public ResponseEntity<List<RatingGetDto>> getAllRating() {
        return ResponseEntity.ok(ratingService.getAllRating());
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<RatingGetDto> getRatingById(@PathVariable("ratingId") Long id) throws RatingNotFoundException {
        return ResponseEntity.ok(ratingService.getRatingById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingGetDto>> getRatingByUserId(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/media/{mediaId}")
    public ResponseEntity<List<RatingGetDto>> getRatingByMediaId(@PathVariable Long mediaId) throws MediaNotFoundException {
        return ResponseEntity.ok(ratingService.getRatingByMediaId(mediaId));
    }

    @PostMapping("/")
    public ResponseEntity<RatingGetDto> addNewRating(@Valid @RequestBody RatingCreateDto ratingCreateDto) throws UserNotFoundException, MediaNotFoundException, DuplicateRatingException {
        return new ResponseEntity<>(ratingService.addNewRating(ratingCreateDto), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{ratingId}")
    public ResponseEntity<RatingGetDto> updateRating(@PathVariable("ratingId") Long ratingId,
                                                     @Valid @RequestBody RatingUpdateRatingDto ratingUpdateRatingDto) throws RatingNotFoundException {
        return new ResponseEntity<>(ratingService.updateRating(ratingId, ratingUpdateRatingDto), HttpStatus.ACCEPTED);
    }
}