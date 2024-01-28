package com.mindera.HelloMam.dtos.updates;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record UserEmailUpdateDto(
        @Valid
        @NotBlank
        String email
) {

}
