package com.mindera.HelloMam.dtos.updates;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import static com.mindera.HelloMam.utils.ValidationMessages.NAME_IS_MANDATORY;

public record UserNameUpdateDto(
        @NotBlank(message = NAME_IS_MANDATORY)
        String name
) {
}
