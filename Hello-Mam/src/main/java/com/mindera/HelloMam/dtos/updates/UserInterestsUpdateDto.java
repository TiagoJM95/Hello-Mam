package com.mindera.HelloMam.dtos.updates;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record UserInterestsUpdateDto(
        @NotEmpty
        List<String> interests
) {
}