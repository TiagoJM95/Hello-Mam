package com.mindera.HelloMam.dtos.creates;

import javax.validation.constraints.NotNull;

import static com.mindera.HelloMam.utils.ValidationMessages.*;

public record RatingCreateDto(
        @NotNull(message = USER_ID_IS_MANDATORY)
        Long userId,
        @NotNull(message = MEDIA_ID_IS_MANDATORY)
        Long mediaId,
        @NotNull(message = RATING_NOT_NULL)
        Float rating
) {
}