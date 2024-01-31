package com.mindera.HelloMam.dtos.updates;

import javax.validation.constraints.NotBlank;

import static com.mindera.HelloMam.utils.ValidationMessages.USERNAME_IS_MANDATORY;

public record UserUpdateUsernameDto(
        @NotBlank(message = USERNAME_IS_MANDATORY)
        String username) {
}