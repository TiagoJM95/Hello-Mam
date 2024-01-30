package com.mindera.HelloMam.dtos.updates;

import javax.validation.constraints.NotNull;

import static com.mindera.HelloMam.utils.ValidationMessages.RATING_NOT_NULL;

public record RatingUpdateRatingDto(
        @NotNull(message = RATING_NOT_NULL)
        Float rating
) {
}