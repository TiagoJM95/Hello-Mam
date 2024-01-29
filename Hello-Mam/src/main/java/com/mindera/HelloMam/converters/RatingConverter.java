package com.mindera.HelloMam.converters;

import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.entities.Rating;
import com.mindera.HelloMam.entities.User;

import static com.mindera.HelloMam.converters.UserConverter.*;
import static com.mindera.HelloMam.converters.MediaConverter.*;

public class RatingConverter {

    public static RatingGetDto fromRatingToRatingDto(Rating rating) {
        return new RatingGetDto(
                rating.getId(),
                toUserGetDto(rating.getUserId()),
                fromMediaToMediaDto(rating.getMediaId()),
                rating.getRating()
        );
    }


    public static Rating fromRatingDtoToRating(RatingCreateDto ratingCreateDto, User user, Media media) {
        return Rating.builder()
                .userId(user)
                .mediaId(media)
                .rating(ratingCreateDto.rating())
                .build();
    }
}