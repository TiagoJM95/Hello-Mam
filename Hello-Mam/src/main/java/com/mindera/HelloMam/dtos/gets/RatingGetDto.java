package com.mindera.HelloMam.dtos.gets;

public record RatingGetDto(
        Long id,
        UserGetDto userId,
        MediaGetDto mediaId,
        Float rating
) {
}