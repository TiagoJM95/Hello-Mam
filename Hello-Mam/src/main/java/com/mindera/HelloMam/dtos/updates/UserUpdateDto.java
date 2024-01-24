package com.mindera.HelloMam.dtos.updates;

import java.time.LocalDate;

public record UserUpdateDto(String username, String name, String email, LocalDate dateOfBirth) {


}
