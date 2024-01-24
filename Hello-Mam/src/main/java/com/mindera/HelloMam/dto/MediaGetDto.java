package com.mindera.HelloMam.dto;

import com.mindera.HelloMam.enums.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record MediaGetDto(

        Integer id,
        @NotBlank(message = "This field is mandatory")
        String refId,
        @NotEmpty(message = "This field is mandatory")
        MediaType type
) {
}
