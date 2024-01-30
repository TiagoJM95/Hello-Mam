package com.mindera.HelloMam.dtos.creates;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

import static com.mindera.HelloMam.utils.ValidationMessages.*;

public record UserCreateDto(
        @NotBlank(message = USERNAME_IS_MANDATORY)
        String username,
        @NotBlank(message = NAME_IS_MANDATORY)
        String name,
        @NotBlank(message = EMAIL_IS_MANDATORY)
        String email,
        List<String> interests,
        @NotNull(message = DATE_IS_MANDATORY)
        @Past(message = DATE_IN_PAST)
        LocalDate dateOfBirth) {
}