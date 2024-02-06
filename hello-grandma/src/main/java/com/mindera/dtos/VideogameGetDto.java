package com.mindera.dtos;

import java.time.LocalDate;
import java.util.List;

public record VideogameGetDto(
        int igdbId,
        String name,
        String releaseDate,
        Double rating,
        List<String> genreIds,
        boolean fromIGDB,
        List<String> similarGames
) {
}