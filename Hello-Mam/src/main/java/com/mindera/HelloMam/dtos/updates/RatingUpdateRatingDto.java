package com.mindera.HelloMam.dtos.updates;

import javax.validation.constraints.NotNull;

public record RatingUpdateRatingDto(
        @NotNull
        Float rating
) {
}