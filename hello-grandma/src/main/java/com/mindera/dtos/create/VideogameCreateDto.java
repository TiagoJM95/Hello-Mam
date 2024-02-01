package com.mindera.dtos.create;

import java.util.List;

public record VideogameCreateDto(int id, String name, String releaseDate, Double rating, List<Integer> genreIds) {
}