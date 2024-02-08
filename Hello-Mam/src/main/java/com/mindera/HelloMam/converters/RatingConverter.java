package com.mindera.HelloMam.converters;

import com.mindera.HelloMam.dtos.creates.RatingCreateDto;
import com.mindera.HelloMam.dtos.gets.RatingGetDto;
import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.entities.Rating;
import com.mindera.HelloMam.entities.User;

import static com.mindera.HelloMam.converters.MediaConverter.fromMediaEntityToMediaGetDto;
import static com.mindera.HelloMam.converters.UserConverter.*;

public class RatingConverter {

    public static RatingGetDto fromRatingEntityToRatingGetDto(Rating rating) {
        return new RatingGetDto(
                rating.getId(),
                fromUserEntityToUserGetDto(rating.getUserId()),
                fromMediaEntityToMediaGetDto(rating.getMediaId()),
                rating.getRating()
        );
    }


    public static Rating fromRatingCreateDtoToRatingEntity(RatingCreateDto ratingCreateDto, User user, Media media) {
        return Rating.builder()
                .userId(user)
                .mediaId(media)
                .rating(ratingCreateDto.rating())
                .build();
    }
}