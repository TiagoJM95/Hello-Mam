package com.mindera.HelloMam.dto;

import com.mindera.HelloMam.enums.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MediaCreateDto(

        @NotBlank(message = "This field is mandatory")
        String refId,
        @NotEmpty(message = "This field is mandatory")
        MediaType type
) {
}