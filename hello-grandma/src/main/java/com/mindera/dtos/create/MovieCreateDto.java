package com.mindera.dtos.create;

import java.util.List;

public record MovieCreateDto(
        String title,
        int year,
        int runtime,
        List<String> genres,
        String director
) {
}