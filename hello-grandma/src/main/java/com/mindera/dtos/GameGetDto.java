package com.mindera.dtos;

import java.util.List;

public record GameGetDto(
        int igdbId,
        String name,
        String releaseDate,
        Double rating,
        List<String> genreIds,
        boolean fromIGDB,
        List<String> similarGames
) {
}