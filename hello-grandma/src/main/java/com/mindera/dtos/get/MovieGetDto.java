package com.mindera.dtos.get;

import org.bson.types.ObjectId;

import java.util.List;

public record MovieGetDto(
        ObjectId id,
        String title,
        int year,
        int runtime,
        List<String> genres,
        String director
) {
}