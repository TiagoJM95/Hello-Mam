package com.mindera.dtos;
import java.util.List;

public record VideogameGetDto(
        int id,
        String title,
        String releaseDate,
        Double rating,
        List<Integer> genreIds,
        boolean fromIGDB
        )



{
}