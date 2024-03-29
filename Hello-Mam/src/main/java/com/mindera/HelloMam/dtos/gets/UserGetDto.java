package com.mindera.HelloMam.dtos.gets;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public record UserGetDto(
        Long id,
        String username,
        String email,
        String name,
        LocalDate dateOfBirth
) implements Serializable {
}