package com.mindera.HelloMam.Dto.Create;

import java.time.LocalDate;

public record UserCreateDto(String username, String name, String email, LocalDate dateOfBirth) {

}
