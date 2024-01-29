package com.mindera.HelloMam.dtos.creates;

import javax.validation.constraints.NotNull;

public record RatingCreateDto(
        @NotNull
        Long userId,
        @NotNull
        Long mediaId,
        @NotNull
        Float rating
) {
}