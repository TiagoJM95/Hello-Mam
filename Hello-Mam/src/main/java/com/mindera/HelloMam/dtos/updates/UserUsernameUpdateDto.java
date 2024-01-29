package com.mindera.HelloMam.dtos.updates;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public record UserUsernameUpdateDto(
        @NotBlank(message = "Username is mandatory")
        String username) {
}