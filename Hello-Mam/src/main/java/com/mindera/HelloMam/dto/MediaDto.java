package com.mindera.HelloMam.dto;

import com.mindera.HelloMam.model.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record MediaDto(

        @NotBlank(message = "This field is mandatory")
        String refId,
        @NotEmpty(message = "The interests field is mandatory")
        MediaType type
) {
}
