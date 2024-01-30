package com.mindera.dtos.get;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

public record MovieGetDto(
        ObjectId id,
        Long tmdbId,
        String title,
        LocalDate releaseDate,
        int runtime,
        List<String> genres,
        String director
) {
}