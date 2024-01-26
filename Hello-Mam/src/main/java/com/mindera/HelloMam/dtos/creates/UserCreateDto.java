package com.mindera.HelloMam.dtos.creates;

import jakarta.persistence.UniqueConstraint;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public record UserCreateDto(
        @NotBlank(message = "Username is mandatory")
        String username,
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotBlank(message = "Email is mandatory")
        String email,
        @NotBlank(message = "Date Of Birth is mandatory")
        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth) {
}