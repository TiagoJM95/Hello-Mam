package com.mindera.HelloMam.dtos.creates;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

public record UserCreateDto(
        @NotBlank(message = "Username is mandatory")
        String username,
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotBlank(message = "Email is mandatory")
        String email,
        List<String> interests,
        @NotNull(message = "Date Of Birth is mandatory")
        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth) {
}