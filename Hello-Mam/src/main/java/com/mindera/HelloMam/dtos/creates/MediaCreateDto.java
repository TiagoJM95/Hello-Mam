package com.mindera.HelloMam.dtos.creates;

import com.mindera.HelloMam.enums.MediaType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MediaCreateDto(

        @Valid
        @NotBlank(message = "This field is mandatory")
        String refId,
       @Valid
        @NotNull(message = "This field is mandatory")
        MediaType type
) {
}
