package com.mindera.HelloMam.dtos.updates;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

import static com.mindera.HelloMam.utils.ValidationMessages.DATE_IN_PAST;
import static com.mindera.HelloMam.utils.ValidationMessages.DATE_IS_MANDATORY;

public record UserDateOfBirthUpdateDto (
        @NotNull(message = DATE_IS_MANDATORY)
        @Past(message = DATE_IN_PAST)
        LocalDate dateOfBirth
){
}