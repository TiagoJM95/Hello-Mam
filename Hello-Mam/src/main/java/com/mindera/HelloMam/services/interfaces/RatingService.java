package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.dtos.updates.RatingUpdateDto;
import com.mindera.HelloMam.exceptions.media_exceptions.MediaNotFoundException;
import com.mindera.HelloMam.exceptions.rating_exceptions.RatingNotFoundException;

import java.util.List;

public interface RatingService {
    List<RatingGetDto> getAllRating();
    RatingGetDto getRatingById(Long id) throws RatingNotFoundException;
    List<RatingGetDto> getRatingByUserId(Long userId) throws Exception;
    List <RatingGetDto> getRatingByMediaId(Long mediaId) throws MediaNotFoundException;
    RatingGetDto addNewRating(RatingCreateDto ratingCreateDto) throws Exception;
    RatingGetDto updateRating(Long ratingId, RatingUpdateDto ratingUpdateDto) throws RatingNotFoundException;

}