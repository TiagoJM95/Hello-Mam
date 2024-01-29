package com.mindera.HelloMam.dtos.updates;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import static com.mindera.HelloMam.utils.ValidationMessages.EMAIL_IS_MANDATORY;

public record UserEmailUpdateDto(
        @NotBlank(message = EMAIL_IS_MANDATORY)
        String email
) {

}
