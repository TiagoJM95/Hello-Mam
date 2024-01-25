package com.mindera.HelloMam.dtos.creates;

import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.entities.User;

public record RatingCreateDto(
        User userId,
        Media mediaId,
        Float rating
) {
}
