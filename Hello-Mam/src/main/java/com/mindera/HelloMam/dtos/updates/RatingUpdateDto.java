package com.mindera.HelloMam.dtos.updates;

import javax.validation.constraints.NotNull;

public record RatingUpdateDto(
        @NotNull
        Float rating
) {
}