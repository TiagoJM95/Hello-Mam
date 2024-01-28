package com.mindera.HelloMam.dtos.gets;

import com.mindera.HelloMam.enums.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MediaGetDto(

        @NotNull
        Long id,
        @NotBlank(message = "This field is mandatory")
        String refId,
        @NotNull(message = "This field is mandatory")
        MediaType type
) {
}
