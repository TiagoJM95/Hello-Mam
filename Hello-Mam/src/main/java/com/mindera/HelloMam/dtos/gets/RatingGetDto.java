package com.mindera.HelloMam.dtos.gets;

import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.entities.User;

public record RatingGetDto(

        Integer id,
        User userId,
        Media mediaId,
        Float rating
) {
}
