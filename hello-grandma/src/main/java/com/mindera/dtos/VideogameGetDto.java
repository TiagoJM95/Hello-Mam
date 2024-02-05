package com.mindera.dtos;
import java.sql.Timestamp;
import java.util.List;

public record VideogameGetDto(
        int igdbId,
        String name,
        String releaseDate,
        Double rating,
        List<Integer> genreIds,
        boolean fromIGDB,
        List<String> similarGames
        )



{
}