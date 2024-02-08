package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.dtos.updates.RatingUpdateRatingDto;
import com.mindera.HelloMam.exceptions.media.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.rating.DuplicateRatingException;
import com.mindera.HelloMam.exceptions.rating.RatingNotFoundException;
import com.mindera.HelloMam.exceptions.user.UserNotFoundException;

import java.util.List;

public interface RatingService {
    List<RatingGetDto> getAllRating();
    RatingGetDto getRatingById(Long id) throws RatingNotFoundException;
    List<RatingGetDto> getRatingByUserId(Long userId) throws UserNotFoundException;
    List <RatingGetDto> getRatingByMediaId(Long mediaId) throws MediaNotFoundException;
    RatingGetDto addNewRating(RatingCreateDto ratingCreateDto) throws UserNotFoundException, MediaNotFoundException, DuplicateRatingException;
    RatingGetDto updateRating(Long ratingId, RatingUpdateRatingDto ratingUpdateRatingDto) throws RatingNotFoundException;
}