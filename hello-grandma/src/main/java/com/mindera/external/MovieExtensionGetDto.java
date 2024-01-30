package com.mindera.external;

import java.util.List;

public record MovieExtensionGetDto(
        long id,
        String title,
        String releaseDate,
        double voteAverage,
        List<String> genres
) {
}