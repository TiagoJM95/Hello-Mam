package com.mindera.dtos.get;

import java.util.List;

public record VideogameGetDto(int id, String name, String releaseDate, Double rating, List<Integer> genreIds) {

}
