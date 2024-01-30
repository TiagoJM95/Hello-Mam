package com.mindera.HelloMam.dtos.updates;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

import static com.mindera.HelloMam.utils.ValidationMessages.INTERESTS_NOT_EMPTY;

public record UserInterestsUpdateDto(
        @NotEmpty(message = INTERESTS_NOT_EMPTY)
        List<String> interests
) {
}