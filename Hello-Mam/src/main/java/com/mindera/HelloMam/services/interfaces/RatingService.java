package com.mindera.HelloMam.services.interfaces;

import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.dtos.updates.RatingUpdateDto;
import com.mindera.HelloMam.entities.Rating;

import java.util.List;

public interface RatingService {
    List<RatingGetDto> getAllRating();
    RatingGetDto getRatingById(Integer id);
    List<RatingGetDto> getRatingByUserId(Integer userId);
    RatingGetDto getRatingByMediaId(Integer mediaId);
    RatingGetDto addNewRating(RatingCreateDto ratingCreateDto);
    RatingGetDto updateRating(Integer ratingId, RatingUpdateDto ratingUpdateDto);

}
