package com.mindera.dtos;
import java.util.List;

public record VideogameGetDto(
        int id,
        String name,
        String releaseDate,
        Double rating,
        List<Integer> genreIds) {
}