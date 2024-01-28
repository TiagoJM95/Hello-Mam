package com.mindera.HelloMam.dtos.creates;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record RatingCreateDto(
        @Valid
        @NotNull
        Long userId,
        @Valid
        @NotNull
        Long mediaId,
        @Valid
        @NotNull
        Float rating
) {
}