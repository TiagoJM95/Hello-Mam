package com.mindera.HelloMam.dtos.creates;

import java.time.LocalDate;

public record UserCreateDto(String username, String name, String email, LocalDate dateOfBirth) {

}
