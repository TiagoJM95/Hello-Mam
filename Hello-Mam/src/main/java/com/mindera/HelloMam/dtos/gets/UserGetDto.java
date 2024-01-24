package com.mindera.HelloMam.dtos.gets;

import java.time.LocalDate;

public record UserGetDto(Long userId, String username, String name, String email, LocalDate dateOfBirth) {
}
