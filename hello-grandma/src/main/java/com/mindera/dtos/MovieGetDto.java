package com.mindera.dtos;

import org.bson.types.ObjectId;

import java.util.List;

public record MovieGetDto(
        ObjectId id,
        Long tmdbId,
        String name,
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