package com.mindera.dtos;

import java.util.List;

public record MovieGetDto(
        Long tmdbId,
        String title,
        String release_date,
        Double vote_average,
        String overview,
        String status,
        String tagline,
        Integer runtime,
        String original_language,
        String revenue,
        String budget,
        String popularity,
        String vote_count,
        List<String> genres
) {
}