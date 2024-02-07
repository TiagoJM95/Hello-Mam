package com.mindera.HelloMam.dtos.gets;

import java.io.Serializable;

public record RatingGetDto(
        Long id,
        UserGetDto userId,
        MediaGetDto mediaId,
        Float rating
) implements Serializable {
}