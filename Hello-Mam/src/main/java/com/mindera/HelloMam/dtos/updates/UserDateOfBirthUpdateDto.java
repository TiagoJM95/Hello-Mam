package com.mindera.HelloMam.dtos.updates;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record UserDateOfBirthUpdateDto (
        @Valid
        @NotBlank
        @Past
        LocalDate dateOfBirth
){

}
