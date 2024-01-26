package com.mindera.HelloMam.dtos.creates;


import javax.validation.constraints.NotNull;

public record RatingCreateDto(
        @NotNull
        Long userId,
        @NotNull
        Integer mediaId,
        @NotNull
        Float rating
) {
}