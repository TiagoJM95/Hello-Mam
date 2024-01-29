package com.mindera.HelloMam.dtos.updates;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record UserDateOfBirthUpdateDto (
        @NotNull
        @Past
        LocalDate dateOfBirth
){
}