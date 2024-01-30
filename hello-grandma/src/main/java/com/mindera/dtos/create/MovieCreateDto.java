package com.mindera.dtos.create;

import java.time.LocalDate;
import java.util.List;

public record MovieCreateDto(
        String title,
        LocalDate releaseDate,
        int runtime,
        List<String> genres,
        String director
) {
}