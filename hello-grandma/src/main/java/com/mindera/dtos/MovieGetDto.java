package com.mindera.dtos;

import org.bson.types.ObjectId;

import java.util.List;

public record MovieGetDto(
        ObjectId id,
        Long tmdbId,
        String title,
        String releaseDate,
        double voteAverage,
        List<String> genres
) {
}