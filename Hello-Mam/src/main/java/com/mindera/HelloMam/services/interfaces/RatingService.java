package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.dtos.updates.RatingUpdateDto;
import com.mindera.HelloMam.exceptions.rating_exceptions.RatingNotFoundException;

import java.util.List;

public interface RatingService {
    List<RatingGetDto> getAllRating();
    RatingGetDto getRatingById(Integer id) throws RatingNotFoundException;
    List<RatingGetDto> getRatingByUserId(Long userId);
    RatingGetDto getRatingByMediaId(Integer mediaId);
    RatingGetDto addNewRating(RatingCreateDto ratingCreateDto);
    RatingGetDto updateRating(Integer ratingId, RatingUpdateDto ratingUpdateDto) throws RatingNotFoundException;

}