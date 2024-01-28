package com.mindera.HelloMam.dtos.gets;

import com.mindera.HelloMam.entities.Media;
import com.mindera.HelloMam.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RatingGetDto(

        @NotNull
        Long id,
        @NotEmpty
        User userId,
        @NotEmpty
        Media mediaId,
        @NotNull
        Float rating
) {
}
