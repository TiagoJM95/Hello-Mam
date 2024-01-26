package com.mindera.HelloMam.dtos.updates;

import javax.validation.constraints.NotBlank;

public record UserUpdateDto(
        @NotBlank(message = "Username is mandatory")
        String username) {
}