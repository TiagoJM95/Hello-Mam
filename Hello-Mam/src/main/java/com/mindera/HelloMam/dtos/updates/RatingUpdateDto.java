package com.mindera.HelloMam.dtos.updates;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record RatingUpdateDto(
        @Valid
        @NotNull
        Float rating
) {
}