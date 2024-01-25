package com.mindera.HelloMam.controllers;

import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.dtos.updates.RatingUpdateDto;
import com.mindera.HelloMam.services.interfaces.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rating")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }


    @GetMapping("/")
    public ResponseEntity<List<RatingGetDto>> getAllRating() {
        return new ResponseEntity<>(ratingService.getAllRating(), HttpStatus.OK);
    }

    @GetMapping(path = "{ratingId}")
    public ResponseEntity<RatingGetDto> getRatingById(@PathVariable("ratingId") Integer id) {
        return new ResponseEntity<>(ratingService.getRatingById(id), HttpStatus.OK);
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<List<RatingGetDto>> getRatingByUserId(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(ratingService.getRatingByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(path = "{mediaId}")
    public ResponseEntity<RatingGetDto> getRatingByMediaId(@PathVariable Integer mediaId) {
        return new ResponseEntity<>(ratingService.getRatingByMediaId(mediaId), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<RatingGetDto> addNewRating(@Valid @RequestBody RatingCreateDto ratingCreateDto) {
        return new ResponseEntity<>(ratingService.addNewRating(ratingCreateDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "{ratingId}")
    public ResponseEntity<RatingGetDto> updateRating(@PathVariable("ratingId") Integer ratingId, @Valid @RequestBody RatingUpdateDto ratingUpdateDto) {
        return new ResponseEntity<>(ratingService.updateRating(ratingId, ratingUpdateDto), HttpStatus.OK);
    }
}
