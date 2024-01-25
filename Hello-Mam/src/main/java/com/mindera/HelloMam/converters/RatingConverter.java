package com.mindera.HelloMam.converters;

import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.dtos.updates.RatingUpdateDto;
import com.mindera.HelloMam.entities.Rating;

public class RatingConverter {

    public static RatingGetDto fromRatingToRatingDto(Rating rating) {
        return new RatingGetDto(
                rating.getId(),
                rating.getUserId(),
                rating.getMediaId(),
                rating.getRating()
        );
    }


    public static Rating fromRatingDtoToRating(RatingCreateDto ratingCreateDto) {
        return Rating.builder()
                .userId(ratingCreateDto.userId())
                .mediaId(ratingCreateDto.mediaId())
                .rating(ratingCreateDto.rating())
                .build();
    }


    public static Rating fromRatingUpdateDtoToRating(RatingUpdateDto ratingUpdateDto) {
        return Rating.builder()
                .rating(ratingUpdateDto.rating())
                .build();
    }

}
