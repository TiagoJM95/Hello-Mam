package com.mindera.HelloMam.dtos.creates;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static com.mindera.HelloMam.utils.ValidationMessages.MEDIA_TYPE_IS_MANDATORY;
import static com.mindera.HelloMam.utils.ValidationMessages.REF_ID_IS_MANDATORY;

public record MediaCreateDto(
        @NotBlank(message = REF_ID_IS_MANDATORY)
        String refId,
        @NotBlank(message = MEDIA_TYPE_IS_MANDATORY)
        String mediaType
) {
}