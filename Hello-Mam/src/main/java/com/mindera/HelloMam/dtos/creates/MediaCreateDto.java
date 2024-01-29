package com.mindera.HelloMam.dtos.creates;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MediaCreateDto(
        @NotBlank(message = "This field is mandatory")
        String refId,
        @NotBlank(message = "This field is mandatory")
        String mediaType
) {
}