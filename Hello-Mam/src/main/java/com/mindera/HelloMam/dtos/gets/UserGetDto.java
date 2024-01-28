package com.mindera.HelloMam.dtos.gets;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import javax.validation.Valid;
import java.time.LocalDate;

public record UserGetDto(

        @NotNull
        Long userId,
        @NotBlank
        String username,
        @NotBlank
        String email,
        @NotBlank
        String name,
        @NotBlank
        @Past
        LocalDate dateOfBirth
) {
}
